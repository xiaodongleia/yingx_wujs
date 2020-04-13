package com.baizhi.service;

import com.baizhi.entity.Log;

import java.util.Map;

public interface LogService {
    void insertlog(Log log);
    public Map<String,Object> queryBuPager(Integer rows, Integer page);
}
