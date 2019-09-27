package com.service;

import com.controller.DataController;

import java.sql.SQLException;
import java.sql.Statement;


public class test {
    static Statement statement;

    public static void main(String[] args) throws SQLException {

//        DataDaoImpl dataDaoImpl = new DataDaoImpl();
//        try {
//            dataDaoImpl.query("select * from cpu WHERE cpu = 'cpu-total' ORDER BY time DESC LIMIT 1");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        InfluxDBtoMySQL influxDBtoMySQL = new InfluxDBtoMySQL();
//
//        String sql = "select * from cpu";
//        Connection mysql = influxDBtoMySQL.getMysql();
//        statement = mysql.createStatement();
//        ResultSet result = statement.executeQuery(sql);
//        String cpu = null;
//        String host = null;
//        while (result.next()) {
//            System.out.println(result.getString(1));
//            System.out.println(result.getString(2));
//            System.out.println(result.getString(3));
//        }
//        System.out.println(result);
//        result.close();
//
//        influxDBtoMySQL.migrate();
        DataController dataController = new DataController();
//        String query = dataController.query();
//        System.out.println(query);
    }
}
