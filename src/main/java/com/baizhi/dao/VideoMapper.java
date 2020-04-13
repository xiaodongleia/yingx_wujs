package com.baizhi.dao;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VideoMapper extends Mapper<Video> {
    List<VideoPo> queryByReleaseTime();
}