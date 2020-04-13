package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper <User>{

    User queryByUsername(String usdername);
    public List<User> selectByPager(@Param("start") Integer start, @Param("rows") Integer rows);
    public Integer selectRecords();
    void updateuser(User user);


    List<User> selectmapsexs();
    List<User> selectmapsex();
}