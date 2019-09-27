package com.dao.migrate;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Con_influxdb_mysql {
//    influxdb information
    private static String urlInfluxdb = "http://192.168.0.33:8086";
//    private static String urlInfluxdb = "http://222.25.188.1:50559";
    private static String userInfluxdb = "root";
    private static String passwordInfluxdb = "xidian320";
    private static String database = "telegraf";
//    mysql information
    private final String dbDriver = "com.mysql.jdbc.Driver";

//    private final String urlMysql = "jdbc:mysql://222.25.188.1:50561/telegraf";
    private final String urlMysql = "jdbc:mysql://192.168.0.33:3306/telegraf";
    private final String userMysql = "root";
    private final String passwordMysql = "xidian320";

    private Connection mysql = null;
    private InfluxDB influxDB;

    public Con_influxdb_mysql() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(urlInfluxdb, userInfluxdb, passwordInfluxdb);
        }
        try {
            Class.forName(dbDriver);
//            mysql = DriverManager.getConnection("jdbc:mysql:http://192.168.0.33:3306/telegraf", "root", "xidian320");
            mysql = DriverManager.getConnection(urlMysql, userMysql, passwordMysql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("连接失败");
        }
    }

    public Connection getMysql() {
        return mysql;
    }

    public InfluxDB getInfluxDB() {
        return influxDB;
    }
}
