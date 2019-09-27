package com.dao.migrate;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class migrate_Net {

    private static String database = "telegraf";
    private  InfluxDB influxDB = null;
    private static QueryResult queryResult;
    private static List<List<Object>> lists;
    private  String command = "select * from net ORDER BY time DESC LIMIT 1";
    private Connection mysql = null;

    //属性
    private String host;
    private double bytes_recv;
    private double bytes_sent;
    private double drop_in;
    private double drop_out;
    private double err_in;
    private double err_out;
    private double packets_recv;
    private double packets_sent;
    private String sql = "insert into net (host,bytes_recv,bytes_sent,drop_in,drop_out,err_in,err_out,packets_recv,packets_sent) " +
            "values (?,?,?,?,?,?,?,?,?);";
    private QueryResult.Result oneResult;

//    @Autowired
//    private Con_influxdb_mysql con_influxdb_mysql;

    Con_influxdb_mysql con_influxdb_mysql = new Con_influxdb_mysql();                 //获取连接对象
    public void migrate() throws SQLException {

        try {
            influxDB = con_influxdb_mysql.getInfluxDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mysql = con_influxdb_mysql.getMysql();
        queryResult = influxDB.query(new Query(command, database));
//        System.out.println(queryResult);
        oneResult = queryResult.getResults().get(0);
        if (oneResult.getSeries() != null) {
            lists = oneResult.getSeries().stream().map(QueryResult.Series::getValues).collect(Collectors.toList()).get(0);
//            strings = oneResult.getSeries().stream().map(QueryResult.Series::getColumns).collect(Collectors.toList()).get(0);
        }
        host = (String) lists.get(0).get(7);
//        System.out.println(device);
        bytes_recv = (double) lists.get(0).get(1);
        bytes_sent = (double) lists.get(0).get(2);
        drop_in = (double) lists.get(0).get(3);
        drop_out = (double) lists.get(0).get(4);
        err_in = (double) lists.get(0).get(5);
        err_out = (double) lists.get(0).get(6);
        packets_recv = (double) lists.get(0).get(63);
        packets_sent = (double) lists.get(0).get(64);


        PreparedStatement preparedStatement = mysql.prepareStatement(sql);
        preparedStatement.setString(1,host);
        preparedStatement.setDouble(2,bytes_recv);
        preparedStatement.setDouble(3, bytes_sent);
        preparedStatement.setDouble(4, drop_in);
        preparedStatement.setDouble(5, drop_out);
        preparedStatement.setDouble(6, err_in);
        preparedStatement.setDouble(7, err_out);
        preparedStatement.setDouble(8, packets_recv);
        preparedStatement.setDouble(9, packets_sent);
        preparedStatement.executeUpdate();

//        mysql.close();
        System.out.println("Net插入完毕: " + lists.get(0).get(0));
    }
}

