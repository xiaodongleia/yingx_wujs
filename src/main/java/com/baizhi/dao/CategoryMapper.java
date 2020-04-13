package com.baizhi.dao;

import com.baizhi.entity.Category;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {
    public List<Category> selectByPager(@Param("start") Integer start, @Param("rows") Integer rows);

    public List<Category> selectByPager1(@Param("start") Integer start, @Param("rows") Integer rows, String uid);

    public Integer selectRecords();

    public Integer selectRecords1(String uid);
    public List<Category>queryAllCategory();
}