package com.dao;

import com.pojo.CpuData;
import com.pojo.DiskData;
import com.pojo.MemData;
import com.pojo.NetData;

import java.util.List;

public interface DataDao {

    CpuData queryOneCpuData();           /*选择mysql最新的一条数据*/

    DiskData queryOneDiskData();

    MemData queryOneMemData();

    NetData queryOneNetData();
}