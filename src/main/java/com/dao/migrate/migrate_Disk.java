package com.dao.migrate;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class migrate_Disk {

    private static String database = "telegraf";
    private  InfluxDB influxDB = null;
    private static QueryResult queryResult;
    private static List<List<Object>> lists;
    private  String command = "select * from disk ORDER BY time DESC LIMIT 1";
    private Connection mysql = null;

    //属性
    private String device;
    private String fstype;
    private String host;
    private String mode;
    private String path;
    private double free;
    private double inodes_free;
    private double inodes_total;
    private double inodes_used;
    private double total;
    private double used;
    private double used_precent;
    private String sql = "insert into disk (device,free,fstype,host,inodes_free,inodes_total,inodes_used,mode,path,total,used,used_precent) values (?,?,?,?,?,?,?,?,?,?,?,?);";
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
        device = (String) lists.get(0).get(1);
//        System.out.println(device);
        free = (double) lists.get(0).get(2);
        fstype = (String) lists.get(0).get(3);
        host = (String) lists.get(0).get(4);
        inodes_free = (double) lists.get(0).get(5);
        inodes_total = (double) lists.get(0).get(6);
        inodes_used = (double) lists.get(0).get(7);
        mode = (String) lists.get(0).get(8);
        path = (String) lists.get(0).get(9);
        total = (double) lists.get(0).get(10);
        used = (double) lists.get(0).get(11);
        used_precent = (double) lists.get(0).get(12);

        PreparedStatement preparedStatement = mysql.prepareStatement(sql);
        preparedStatement.setString(1,device);
        preparedStatement.setDouble(2,free);
        preparedStatement.setString(3, fstype);
        preparedStatement.setString(4, host);
        preparedStatement.setDouble(5, inodes_free);
        preparedStatement.setDouble(6, inodes_total);
        preparedStatement.setDouble(7, inodes_used);
        preparedStatement.setString(8, mode);
        preparedStatement.setString(9, path);
        preparedStatement.setDouble(10, total);
        preparedStatement.setDouble(11, used);
        preparedStatement.setDouble(12, used_precent);
        preparedStatement.executeUpdate();

//        mysql.close();
        System.out.println("Disk插入完毕: " + lists.get(0).get(0));
    }
}

