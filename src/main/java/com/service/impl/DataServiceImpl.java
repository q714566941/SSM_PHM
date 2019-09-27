package com.service.impl;

import com.dao.DataDao;
import com.google.gson.JsonObject;
import com.pojo.CpuData;
import com.pojo.DiskData;
import com.pojo.MemData;
import com.pojo.NetData;
import com.service.DataService;
import javafx.scene.input.DataFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Timer;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDao dataDao;

    @Override
    public String queryOneCpuData() {
        System.out.println("DataServiceImpl_Cpu");
        CpuData cpuData = dataDao.queryOneCpuData();
        //timestamp格式
        Timestamp timeCreate = cpuData.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeFormat = sdf.format(timeCreate);

//        System.out.println("cpu_timeS: " + timeFormat);

//        System.out.println("timeCreate: " + timeCreate);
        System.out.println(cpuData);
        JSONArray jsonArray = JSONArray.fromObject(cpuData);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        jsonObject.put("timeFormat", timeFormat);
        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(jsonObject);
        System.out.println(jsonArray1.toString());
        return jsonArray1.toString();
    }

    @Override
    public String queryOneDiskData() {
        System.out.println("DataServiceImpl_Disk");
        DiskData diskData = dataDao.queryOneDiskData();
        System.out.println("get diskData!");
        //timestamp格式
        Timestamp timeCreate = diskData.getTimeCreate();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeFormat = sdf.format(timeCreate);

//        System.out.println("disk_timeS: " + timeFormat);
//        System.out.println("timeCreate: " + timeCreate);
        System.out.println(diskData);

        JSONArray jsonArray = JSONArray.fromObject(diskData);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        jsonObject.put("timeFormat", timeFormat);
        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(jsonObject);
        System.out.println(jsonArray1.toString());
        return jsonArray1.toString();
    }

    @Override
    public String queryOneMemData() {
        System.out.println("DataServiceImpl_mem");
        MemData memData = dataDao.queryOneMemData();
        System.out.println("geted menData!");
        //timestamp格式
        Timestamp timeCreate = memData.getTimeCreate();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeFormat = sdf.format(timeCreate);

//        System.out.println("disk_timeS: " + timeFormat);
//        System.out.println("timeCreate: " + timeCreate);
        System.out.println(memData);

        JSONArray jsonArray = JSONArray.fromObject(memData);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        jsonObject.put("timeFormat", timeFormat);
        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(jsonObject);
        System.out.println(jsonArray1.toString());
        return jsonArray1.toString();
    }

    @Override
    public String queryOneNetData() {
        System.out.println("DataServiceImpl_net");

        float uploadS;
        float downloadS;
        NetData netData1 = dataDao.queryOneNetData();
        long bytes_recv1 = netData1.getBytes_recv();
        long bytes_sent1 = netData1.getBytes_sent();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NetData netData2 = dataDao.queryOneNetData();
        long bytes_recv2 = netData2.getBytes_recv();
        long bytes_sent2 = netData2.getBytes_sent();

        downloadS = ((bytes_recv2 - bytes_recv1) / 10);             //10s
        uploadS = ((bytes_sent2 - bytes_sent1) / 10);

        System.out.println("uploadS: " + uploadS);
        System.out.println("downloadS: " + downloadS);

        Timestamp timeCreate = netData2.getTimeCreate();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeFormat = sdf.format(timeCreate);

        System.out.println(netData2);

        JSONArray jsonArray = JSONArray.fromObject(netData2);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        jsonObject.put("timeFormat", timeFormat);
        //向netData2中插入变量
        jsonObject.put("uploadS", uploadS);
        jsonObject.put("downloadS", downloadS);
        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(jsonObject);
        System.out.println(jsonArray1.toString());
        return jsonArray1.toString();
    }
}