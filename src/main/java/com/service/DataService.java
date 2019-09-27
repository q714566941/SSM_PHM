package com.service;

import com.pojo.CpuData;

import java.util.List;

public interface DataService {

    String queryOneCpuData();

    String queryOneDiskData();

    String queryOneMemData();

    String queryOneNetData();
}