package com.baizhi.dao;

import com.baizhi.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDao {
    void insertlog(Log log);
    public List<Log> selectByPager(@Param("start") Integer start, @Param("rows") Integer rows);
    public Integer selectRecords();
}
