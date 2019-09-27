package com.dao.migrate;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class migrate_Mem {

    private static String database = "telegraf";
    private  InfluxDB influxDB = null;
    private static QueryResult queryResult;
    private static List<List<Object>> lists;
    private  String command = "select * from mem ORDER BY time DESC LIMIT 1";
    private Connection mysql = null;

    //属性
    private double active;
    private double available;
    private double buffered;
    private double cached;
    private double commit_limit;
    private double committed_as;
    private double dirty;
    private double free;
    private double high_free;
    private double high_total;
    private double huge_page_size;
    private double huge_pages_free;
    private double huge_pages_total;
    private double inactive;
    private double low_free;
    private double low_total;
    private double mapped;
    private double page_tables;
    private double shared;
    private double slab;
    private double swap_cached;
    private double swap_free;
    private double swap_total;
    private double used ;
    private double total;
    private double vmalloc_chunk;
    private double vmalloc_total;
    private double vmalloc_used;
    private double wired;
    private double write_back;
    private double write_back_tmp;
    private double available_percent;
    private double used_percent;
    private String host;
    private String sql = "insert into mem (active,available,buffered,cached,commit_limit,committed_as," +
            "dirty,free,high_free,high_total,huge_page_size,huge_pages_free,huge_pages_total,inactive," +
            "low_free,low_total,mapped,page_tables,shared,slab,swap_cached,swap_free,swap_total,total,used," +
            "vmalloc_chunk,vmalloc_total,vmalloc_used,wired,write_back,write_back_tmp,available_percent,used_percent,host) " +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
        active = (double) lists.get(0).get(1);
//        System.out.println(active);
        available = (double) lists.get(0).get(2);
        available_percent = (double) lists.get(0).get(3);
        buffered = (double) lists.get(0).get(4);
        cached = (double) lists.get(0).get(5);
        commit_limit = (double) lists.get(0).get(7);
        committed_as = (double) lists.get(0).get(6);
        dirty = (double) lists.get(0).get(8);
        free = (double) lists.get(0).get(9);
        high_free = (double) lists.get(0).get(10);
        high_total = (double) lists.get(0).get(11);
        host = (String) lists.get(0).get(12);
        huge_page_size = (double) lists.get(0).get(13);
        huge_pages_free = (double) lists.get(0).get(14);
        huge_pages_total = (double) lists.get(0).get(15);
        inactive = (double) lists.get(0).get(16);
        low_free = (double) lists.get(0).get(17);
        low_total = (double) lists.get(0).get(18);
        mapped = (double) lists.get(0).get(19);
        page_tables = (double) lists.get(0).get(20);
        shared = (double) lists.get(0).get(21);
        slab = (double) lists.get(0).get(22);
        swap_cached = (double) lists.get(0).get(23);
        swap_free = (double) lists.get(0).get(24);
        swap_total = (double) lists.get(0).get(25);
        total = (double) lists.get(0).get(26);
        used = (double) lists.get(0).get(27);
        used_percent = (double) lists.get(0).get(28);
        vmalloc_chunk = (double) lists.get(0).get(29);
        vmalloc_total = (double) lists.get(0).get(30);
        vmalloc_used = (double) lists.get(0).get(31);
        wired = (double) lists.get(0).get(32);
        write_back = (double) lists.get(0).get(33);
        write_back_tmp = (double) lists.get(0).get(34);

        PreparedStatement preparedStatement = mysql.prepareStatement(sql);
        preparedStatement.setDouble(1,active);
        preparedStatement.setDouble(2,available);
        preparedStatement.setDouble(3, buffered);
        preparedStatement.setDouble(4, cached);
        preparedStatement.setDouble(5, commit_limit);
        preparedStatement.setDouble(6, committed_as);
        preparedStatement.setDouble(7, dirty);
        preparedStatement.setDouble(8, free);
        preparedStatement.setDouble(9, high_free);
        preparedStatement.setDouble(10, high_total);
        preparedStatement.setDouble(11, huge_page_size);
        preparedStatement.setDouble(12, huge_pages_free);
        preparedStatement.setDouble(13, huge_pages_total);
        preparedStatement.setDouble(14, inactive);
        preparedStatement.setDouble(15, low_free);
        preparedStatement.setDouble(16, low_total);
        preparedStatement.setDouble(17, mapped);
        preparedStatement.setDouble(18, page_tables);
        preparedStatement.setDouble(19, shared);
        preparedStatement.setDouble(20, slab);
        preparedStatement.setDouble(21, swap_cached);
        preparedStatement.setDouble(22, swap_free);
        preparedStatement.setDouble(23, swap_total);
        preparedStatement.setDouble(24, total);
        preparedStatement.setDouble(25, used);
        preparedStatement.setDouble(26, vmalloc_chunk);
        preparedStatement.setDouble(27, vmalloc_total);
        preparedStatement.setDouble(28, vmalloc_used);
        preparedStatement.setDouble(29, wired);
        preparedStatement.setDouble(30, write_back);
        preparedStatement.setDouble(31, write_back_tmp);
        preparedStatement.setDouble(32, available_percent);
        preparedStatement.setDouble(33, used_percent);
        preparedStatement.setString(34, host);
        preparedStatement.executeUpdate();

//        mysql.close();
        System.out.println("Men插入完毕: " + lists.get(0).get(0));
    }
}

