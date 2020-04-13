package com.baizhi.service;


import com.baizhi.entity.Video;
import com.baizhi.vo.VideoVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface VideoService {

    HashMap<String, Object> queryByPage(Integer page, Integer rows);

    String add(Video video);

    void update(Video video);

    void uploadVdieo(MultipartFile path, String id, HttpServletRequest request);

    void uploadVdieos(MultipartFile path, String id, HttpServletRequest request);

    HashMap<String, Object> delete(Video video);

    List<VideoVo> queryByReleaseTime();
    //¼ìË÷
    List<Video> querySearch(String content);

    List<Video> querySearchs(String content);
}
