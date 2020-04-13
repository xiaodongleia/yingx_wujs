package com.baizhi.dao;

import com.baizhi.entity.FeedBack;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FeedBackMapper extends Mapper<FeedBack> {
    public List<FeedBack> selectByPager(@Param("start") Integer start, @Param("rows") Integer rows);
    public Integer selectRecords();
}