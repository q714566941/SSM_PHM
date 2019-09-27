package com.dao.migrate;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class migrate_CPU {

    private static String database = "telegraf";
    private  InfluxDB influxDB = null;
    private static QueryResult queryResult;
    private static List<List<Object>> lists;
    private  String command = "select * from cpu WHERE cpu = 'cpu-total' ORDER BY time DESC LIMIT 1";
    private Connection mysql = null;

    //属性
    private String cpu;
    private String host;
    private double usage_guest;
    private double usage_guest_nice;
    private double usage_idle;
    private double usage_iowait;
    private double usage_irq;
    private double usage_nice;
    private double usage_softirq;
    private double usage_steal;
    private double usage_system;
    private double usage_user;
    private String sql = "insert into cpu (cpu,host,usage_guest,usage_guest_nice,usage_idle,usage_iowait,usage_irq,usage_nice,usage_softirq,usage_steal,usage_system,usage_user) values (?,?,?,?,?,?,?,?,?,?,?,?);";
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
        cpu = (String) lists.get(0).get(1);
//        System.out.println(cpu);
        host = (String) lists.get(0).get(2);
        usage_guest = (double) lists.get(0).get(3);
        usage_guest_nice = (double) lists.get(0).get(4);
        usage_idle = (double) lists.get(0).get(5);
        usage_iowait = (double) lists.get(0).get(6);
        usage_irq = (double) lists.get(0).get(7);
        usage_nice = (double) lists.get(0).get(8);
        usage_softirq = (double) lists.get(0).get(9);
        usage_steal = (double) lists.get(0).get(10);
        usage_system = (double) lists.get(0).get(11);
        usage_user = (double) lists.get(0).get(12);

        PreparedStatement preparedStatement = mysql.prepareStatement(sql);
        preparedStatement.setString(1,cpu);
        preparedStatement.setString(2,host);
        preparedStatement.setDouble(3, usage_guest);
        preparedStatement.setDouble(4, usage_guest_nice);
        preparedStatement.setDouble(5, usage_idle);
        preparedStatement.setDouble(6, usage_iowait);
        preparedStatement.setDouble(7, usage_irq);
        preparedStatement.setDouble(8, usage_nice);
        preparedStatement.setDouble(9, usage_softirq);
        preparedStatement.setDouble(10, usage_steal);
        preparedStatement.setDouble(11, usage_system);
        preparedStatement.setDouble(12, usage_user);
        preparedStatement.executeUpdate();

//        mysql.close();
        System.out.println("CPU插入完毕: " + lists.get(0).get(0));
    }
}

