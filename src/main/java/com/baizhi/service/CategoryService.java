package com.baizhi.service;

import com.baizhi.entity.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CategoryService {

    public Map<String,Object> queryBuPager(Integer rows, Integer page);
    public Map<String,Object> queryBuPager1(Integer rows, Integer page,String uid);
    public void insert(Category category);
    HashMap<String,Object> delete(Category category);
    void update(Category category);
    public List<Category> queryAllCategory();
}
