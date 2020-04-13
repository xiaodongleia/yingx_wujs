package com.baizhi.dao;

import com.baizhi.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    public List<Category> selectAll(String levels);

    public List<Category> selectByPager(@Param("start") Integer start, @Param("rows") Integer rows);

    public List<Category> selectByPager1(@Param("start") Integer start, @Param("rows") Integer rows, String uid);

    public Integer selectRecords();

    public Integer selectRecords1(String uid);

    public void insert(Category category);
}
