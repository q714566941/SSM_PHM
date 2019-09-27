$(function () {
    echart_1();
    echart_2();

    echart_3();
    echart_4();

    // echart_map();
    echart_5();

    //获取项目路径
    function getRootPath() {
        var curWwwPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        var localhostPaht = curWwwPath.substring(0, pos);
        var projectName = pathName
            .substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName);               /*http://localhost:8080/first_war_exploded/*/
    }


    //echart_1
    function echart_1() {

        var timeList = [];
        var cpuList = [];
        function getData() {
            var lineChart = echarts.init(document.getElementById('chart_1'));

            $.ajax({
                type: "POST",
                async: true,
                url: getRootPath() + "/service/cpudata",
                contentType: "application/json; charset=utf-8",
                dataType:"JSON",
                success:function (result) {
                    if (result) {
                        for (var i = 0; i < result.length; i++) {
                            if (result[i] != null) {
                                // console.log(result.timeFormat)
                                timeList.push(result[i].timeFormat);
                                cpuList.push(result[i].usage_user);
                            }
                        }
                        if (cpuList.length >= 30) {
                            timeList.shift();
                            cpuList.shift();
                        }
                        lineChart.hideLoading();
                        lineChart.setOption({               /*ajax每次请求成功后就画图*/
                            title:{
                                text:'CPU_user%',
                                textStyle: {
                                    color: "white",
                                    fontSize:16,
                                }
                            },
                            tooltip: {                      //动态的提示
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'cross',
                                    label: {
                                        backgroundColor: 'black'
                                    }
                                }
                            },
                            xAxis: {
                                type: 'category',
                                data: timeList,
                                boundaryGap: false,
                                axisLine:{
                                    lineStyle: {
                                        color:'white',
                                    }
                                },
                            },
                            yAxis: {
                                type: 'value',
                                axisLine:{
                                    lineStyle: {
                                        color:'white',
                                    }
                                }

                            },
                            // tooltip:{                        //静态的提示
                            //     trigger: 'item',
                            //     formatter: '{a}:{b}<br\>{c}'
                            // },
                            series: [{
                                // name:'user(%)',
                                data: cpuList,
                                type: 'line',
                            }]
                        });
                    }
                }
            })
        }
        //连续画图
        window.setInterval(getData, 6000);
    }

    //echart_2
    function echart_2() {
        // 基于准备好的dom，初始化echarts实例
        var timeList = [];
        var memList = [];
        function getData() {
            var lineChart = echarts.init(document.getElementById('chart_2'));

            $.ajax({
                type: "POST",
                async: true,
                url: getRootPath() + "/service/memdata",
                contentType: "application/json; charset=utf-8",
                dataType:"JSON",
                success:function (result) {
                    if (result) {
                        for (var i = 0; i < result.length; i++) {
                            if (result[i] != null) {
                                // console.log(result.timeFormat)
                                timeList.push(result[i].timeFormat);
                                memList.push(result[i].used_percent);
                            }
                        }
                        if (memList.length >= 30) {
                            timeList.shift();
                            memList.shift();
                        }
                        lineChart.hideLoading();
                        lineChart.setOption({               /*ajax每次请求成功后就画图*/
                            title: {
                                text: '内存使用情况',
                                textStyle: {
                                    color: "white",
                                    fontSize:16,
                                }
                            },
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'cross'
                                }
                            },
                            toolbox: {
                                show: false,
                                feature: {
                                    saveAsImage: {}
                                }
                            },
                            xAxis:  {
                                type: 'category',
                                boundaryGap: false,           //???
                                data: timeList,
                                axisLine:{
                                    lineStyle: {
                                        color:'white',
                                    }
                                },
                            },
                            yAxis: {
                                type: 'value',
                                axisLine:{
                                    lineStyle: {
                                        color:'white',
                                    }
                                },
                                axisLabel: {
                                    formatter: '{value} %',
                                },

                                axisPointer: {
                                    snap: true
                                }
                            },
                            visualMap: {
                                top: 10,
                                right: 10,
                                textStyle: {
                                    color: 'rgba(244,248,243,0)',              //让文字完全透明了

                                },
                                pieces: [{
                                    gt: 0,
                                    lte: 20,
                                    color: '#25ff02'
                                }, {
                                    gt: 20,
                                    lte: 40,
                                    color: '#ccff60'
                                }, {
                                    gt: 40,
                                    lte: 50,
                                    color: '#fffa5c'
                                }, {
                                    gt: 50,
                                    lte: 70,
                                    color: '#ffd358'
                                }, {
                                    gt: 70,
                                    lte: 90,
                                    color: '#ff893b'
                                }, {
                                    gt: 90,
                                    color: '#ff3119'
                                }],
                                outOfRange: {
                                    color: '#999'
                                }
                            },
                            series: [
                                {
                                    name:'使用率',
                                    type:'line',
                                    smooth: true,
                                    data: memList,
                                }
                            ]
                        });
                    }
                }
            })
        }
        //连续画图
        window.setInterval(getData, 6000);
    }
    
    // echart_map
    function echart_map() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_map'));

        var name_title = "投票统计"
        var subname = ''
        var nameColor = " rgb(55, 75, 113)"
        var name_fontFamily = '楷体'
        var name_fontSize = 52
        var mapName = 'china'
        var data = []
        var geoCoordMap = {};
        var toolTipData = [];

        /*获取地图数据*/
        myChart.showLoading();
        var mapFeatures = echarts.getMap(mapName).geoJson.features;
        myChart.hideLoading();
        mapFeatures.forEach(function(v) {
            // 地区名称
            var name = v.properties.name;
            // 地区经纬度
            geoCoordMap[name] = v.properties.cp;
            data.push({
                name: name,
                value: Math.round(Math.random() * 100 + 10)
            })
            toolTipData.push({
                name: name,
                value: [{
                    name: "客运车",
                    value: Math.round(Math.random() * 100 + 10)
                },
                    {
                        name: "危险品运输车",
                        value: Math.round(Math.random() * 100 + 10)
                    },
                    {
                        name: "网约车",
                        value: Math.round(Math.random() * 100 + 10)
                    },
                    {
                        name: "学生班车",
                        value: Math.round(Math.random() * 100 + 10)
                    }
                ]
            })
        });

        var max = 480,
            min = 9; // todo
        var maxSize4Pin = 100,
            minSize4Pin = 20;

        var convertData = function(data) {
            var res = [];
            for (var i = 0; i < data.length; i++) {
                var geoCoord = geoCoordMap[data[i].name];
                if (geoCoord) {
                    res.push({
                        name: data[i].name,
                        value: geoCoord.concat(data[i].value),
                    });
                }
            }
            return res;
        };
        option = {
            title: {
                show:false,
                text: name_title,
                subtext: subname,
                x: 'center',
                textStyle: {
                    color: nameColor,
                    fontFamily: name_fontFamily,
                    fontSize: name_fontSize
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: function(params) {
                    if (typeof(params.value)[2] == "undefined") {
                        var toolTiphtml = ''
                        for(var i = 0;i<toolTipData.length;i++){
                            if(params.name==toolTipData[i].name){
                                toolTiphtml += toolTipData[i].name+':<br>'
                                for(var j = 0;j<toolTipData[i].value.length;j++){
                                    toolTiphtml+=toolTipData[i].value[j].name+':'+toolTipData[i].value[j].value+"<br>"
                                }
                            }
                        }
                        return toolTiphtml;
                    } else {
                        var toolTiphtml = ''
                        for(var i = 0;i<toolTipData.length;i++){
                            if(params.name==toolTipData[i].name){
                                toolTiphtml += toolTipData[i].name+':<br>'
                                for(var j = 0;j<toolTipData[i].value.length;j++){
                                    toolTiphtml+=toolTipData[i].value[j].name+':'+toolTipData[i].value[j].value+"<br>"
                                }
                            }
                        }
                        return toolTiphtml;
                    }
                }
            },
            legend: {
                orient: 'vertical',
                y: 'bottom',
                x: 'right',
                data: ['credit_pm2.5'],
                textStyle: {
                    color: '#fff'
                }
            },
            visualMap: {
                show: false,
                min: 0,
                max: 500,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'], // 文本，默认为数值文本
                calculable: true,
                seriesIndex: [1],
                inRange: {
                    // color: ['#3B5077', '#031525'] // 蓝黑
                    // color: ['#ffc0cb', '#800080'] // 红紫
                    // color: ['#3C3B3F', '#605C3C'] // 黑绿
                    color: ['#0f0c29', '#302b63', '#24243e'] // 黑紫黑
                    // color: ['#23074d', '#cc5333'] // 紫红
                    // color: ['#00467F', '#A5CC82'] // 蓝绿
                    // color: ['#1488CC', '#2B32B2'] // 浅蓝
                    // color: ['#00467F', '#A5CC82'] // 蓝绿
                    // color: ['#00467F', '#A5CC82'] // 蓝绿
                    // color: ['#00467F', '#A5CC82'] // 蓝绿
                    // color: ['#00467F', '#A5CC82'] // 蓝绿

                }
            },
            /*工具按钮组*/
            toolbox: {
                show: false,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: {
                        readOnly: false
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            geo: {
                show: true,
                map: mapName,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: false,
                    }
                },
                roam: true,
                itemStyle: {
                    normal: {
                        areaColor: '#031525',
                        borderColor: '#3B5077',
                    },
                    emphasis: {
                        areaColor: '#2B91B7',
                    }
                }
            },
            series: [{
                name: '散点',
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(data),
                symbolSize: function(val) {
                    return val[2] / 10;
                },
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#05C3F9'
                    }
                }
            },
                {
                    type: 'map',
                    map: mapName,
                    geoIndex: 0,
                    aspectScale: 0.75, //长宽比
                    showLegendSymbol: false, // 存在legend时显示
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: false,
                            textStyle: {
                                color: '#fff'
                            }
                        }
                    },
                    roam: true,
                    itemStyle: {
                        normal: {
                            areaColor: '#031525',
                            borderColor: '#0227ad',
                        },
                        emphasis: {
                            areaColor: '#2B91B7'
                        }
                    },
                    animation: false,
                    data: data
                },
                {
                    name: '点',
                    type: 'scatter',
                    coordinateSystem: 'geo',
                    symbol: 'pin', //气泡
                    symbolSize: function(val) {
                        var a = (maxSize4Pin - minSize4Pin) / (max - min);
                        var b = minSize4Pin - a * min;
                        b = maxSize4Pin - a * max;
                        return a * val[2] + b;
                    },
                    label: {
                        //气泡上的文字
                        normal: {
                            show: false,
                            textStyle: {
                                color: '#fff',
                                fontSize: 9,
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#F62157', //标志颜色
                        }
                    },
                    zlevel: 6,
                    data: convertData(data),
                },
                {
                    name: 'Top 5',
                    type: 'effectScatter',
                    coordinateSystem: 'geo',
                    data: convertData(data.sort(function(a, b) {
                        return b.value - a.value;
                    }).slice(0, 5)),
                    symbolSize: function(val) {
                        return val[2] / 10;
                    },
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#05C3F9',
                            shadowBlur: 10,
                            shadowColor: '#05C3F9'
                        }
                    },
                    zlevel: 1
                },

            ]
        };
        myChart.setOption(option);

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });

}

    //echart_3
    function echart_3() {
        // 基于准备好的dom，初始化echarts实例
        var free;
        var used;
        function getData() {
            var lineChart = echarts.init(document.getElementById('chart_3'));

            $.ajax({
                type: "POST",
                async: true,
                url: getRootPath() + "/service/diskdata",
                contentType: "application/json; charset=utf-8",
                dataType:"JSON",
                success:function (result) {
                    if (result) {
                        for (var i = 0; i < result.length; i++) {
                            if (result[i] != null) {
                                // console.log(result.timeFormat)
                                free = parseInt((result[i].free)/(1024*1024*1024));
                                used = parseInt((result[i].used) / (1024*1024*1024));
                            }
                        }

                        lineChart.hideLoading();
                        lineChart.setOption({               /*ajax每次请求成功后就画图*/
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b}: {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                x: 'left',
                                textStyle: {
                                    color: ['#ff2c16', '#20d908'],
                                },
                                data:['已用','剩余']
                            },
                            series: [
                                // {
                                //     name:'访问来源',
                                //     type:'pie',
                                //     selectedMode: 'single',
                                //     radius: [0, '30%'],
                                //
                                //     label: {
                                //         normal: {
                                //             position: 'inner'
                                //         }
                                //     },
                                //     labelLine: {
                                //         normal: {
                                //             show: false
                                //         }
                                //     },
                                //     data:[
                                //         // {value:335, name:'已用', selected:true},
                                //         // {value:679, name:'营销广告'},
                                //         // {value:1548, name:'剩余'}
                                //     ]
                                // },

                                {
                                    radius: '40%',

                                    name:'使用情况',
                                    type:'pie',
                                    // radius: ['40%', '55%'],      //控制是否有环
                                    label: {
                                        normal: {
                                            formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                                            backgroundColor: '#d6d8dc',
                                            borderColor: '#a4a4a4',
                                            borderWidth: 1,
                                            borderRadius: 4,
                                            // shadowBlur:3,
                                            // shadowOffsetX: 2,
                                            // shadowOffsetY: 2,
                                            // shadowColor: '#999',
                                            // padding: [0, 7],
                                            rich: {
                                                a: {
                                                    color: '#999',
                                                    lineHeight: 15,
                                                    align: 'center'
                                                },
                                                // abg: {
                                                //     backgroundColor: '#333',
                                                //     width: '100%',
                                                //     align: 'right',
                                                //     height: 22,
                                                //     borderRadius: [4, 4, 0, 0]
                                                // },
                                                hr: {
                                                    borderColor: '#aaa',
                                                    width: '100%',
                                                    borderWidth: 0.5,
                                                    height: 0
                                                },
                                                b: {
                                                    fontSize: 12,
                                                    lineHeight: 20
                                                },
                                                per: {
                                                    color: '#eee',
                                                    backgroundColor: '#334455',
                                                    padding: [2, 4],
                                                    borderRadius: 2
                                                }
                                            }
                                        }
                                    },
                                    color: ['#ff592a', '#1eb808'],
                                    data:[
                                        {value:used, name:'已用', selected:true},
                                        {value:free, name:'剩余'}
                                    ]
                                }
                            ]
                        });
                    }
                }
            })
        }
        //连续画图
        window.setInterval(getData, 1000);
    }
    
    function echart_4() {
        var uploadSpend;
        var downloadSpend;
        function getData() {
            var myChart = echarts.init(document.getElementById('chart_4'));


            $.ajax({
                type: "POST",
                async: true,
                url: getRootPath() + "/service/netdata",
                contentType: "application/json; charset=utf-8",
                dataType: "JSON",
                success: function (result) {
                    if (result) {
                        for (var i = 0; i < result.length; i++) {
                            if (result[i] != null) {
                                console.log("hello word!");
                                uploadSpend = ((result[i].uploadS) / (1024)).toFixed(2);
                                downloadSpend = ((result[i].downloadS) / (1024)).toFixed(2);
                            }
                            console.log(uploadSpend);
                            console.log(downloadSpend);
                        }

                        myChart.hideLoading();
                        myChart.setOption({               /*ajax每次请求成功后就画图*/
                            tooltip: {
                                formatter: "{a} <br/>{b} : {c}%"
                            },
                            toolbox: {
                                // feature: {
                                //     restore: {},
                                //     saveAsImage: {}
                                // },
                                show: false
                            },
                            series: [
                                {
                                    name: '上行网速',
                                    type: 'gauge',
                                    center: ['25%', '48%'],
                                    radius: '60%',
                                    min: 0,
                                    max: 500,
                                    splitNumber: 5,
                                    data: [{value: uploadSpend, name: '上行网速'}],
                                    detail: {
                                        formatter: '{value}',
                                        textStyle: {color: 'white', fontSize: 20},
                                    },
                                    title: {               //设置仪表盘中间显示文字样式
                                        offsetCenter: [0, '100%'],      //标题位置
                                        textStyle: {
                                            fontWeight: 'bolder',
                                            fontSize: 18,
                                            fontStyle: 'italic',
                                            color: "white"
                                        }
                                    },
                                    axisLine: {            // 坐标轴线
                                        lineStyle: {       // 属性lineStyle控制线条样式
                                            color: [[0.2, '#228b22'], [0.8, '#48b'], [1, '#ff4500']],
                                            width: 8
                                        }
                                    },
                                    axisTick: {            // 坐标轴小标记
                                        splitNumber: 10,   // 每份split细分多少段
                                        length: 11,        // 属性length控制线长
                                        lineStyle: {       // 属性lineStyle控制线条样式
                                            color: 'auto'
                                        }
                                    },
                                    splitLine: {           // 分隔线
                                        show: true,        // 默认显示，属性show控制显示与否
                                        length: 17,         // 属性length控制线长
                                        lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                                            color: '#ffffff'
                                        }
                                    },
                                },
                                {
                                    name: '下行网速',
                                    type: 'gauge',
                                    center: ['75%', '48%'],
                                    radius: '60%',
                                    min: 0,
                                    max: 500,
                                    splitNumber: 5,
                                    data: [{value: downloadSpend, name: '下行网速'}],
                                    detail: {
                                        formatter: '{value}',
                                        textStyle: {color: 'white', fontSize: 20},
                                    },
                                    title: {               //设置仪表盘中间显示文字样式
                                        offsetCenter: [0, '100%'],             //标题位置
                                        textStyle: {
                                            fontWeight: 'bolder',
                                            fontSize: 18,
                                            fontStyle: 'italic',
                                            color: "white"
                                        }
                                    },
                                    axisLine: {            // 坐标轴线
                                        lineStyle: {       // 属性lineStyle控制线条样式
                                            color: [[0.2, '#228b22'], [0.8, '#48b'], [1, '#ff4500']],
                                            width: 8
                                        }
                                    },
                                    axisTick: {            // 坐标轴小标记
                                        splitNumber: 10,   // 每份split细分多少段
                                        length: 11,        // 属性length控制线长
                                        lineStyle: {       // 属性lineStyle控制线条样式
                                            color: 'auto'
                                        }
                                    },
                                    splitLine: {           // 分隔线
                                        show: true,        // 默认显示，属性show控制显示与否
                                        length: 17,         // 属性length控制线长
                                        lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                                            color: '#ffffff'
                                        }
                                    },
                                }
                            ]
                        });
                    }
                }
            })
        }
        window.setInterval(getData, 3000);
    }

    //test
    function echart_5() {
        // 基于准备好的dom，初始化echarts实例
        // var dom = document.getElementById("center_left_top");
        // var myChart = echarts.init(dom);
        var myChart = echarts.init(document.getElementById('chart_5'));
        var app = {};
        option = null;
        option = {
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            // toolbox: {                                   //右上角“保存为图”
            //     feature: {
            //         restore: {},
            //         saveAsImage: {}
            //     }
            // },
            series: [
                {
                    name: '业务指标',
                    type: 'gauge',
                    detail: {
                        formatter:'{value}',
                        textStyle:{color:'white',fontSize:20},
                    },
                    title : {               //设置仪表盘中间显示文字样式
                        textStyle: {
                            fontWeight: 'bolder',
                            fontSize: 18,
                            fontStyle: 'italic',
                            color:"white"
                        }
                    },
                    data: [{value: 50, name: '健康值'}]
                }
            ]
        };

        setInterval(function () {
            option.series[0].data[0].value = (Math.random() * 100).toFixed(0) - 0;
            myChart.setOption(option, true);
        },2000);
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }




})
