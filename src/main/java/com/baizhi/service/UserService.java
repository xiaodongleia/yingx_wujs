package com.baizhi.service;

import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {

    String add(User user);

    void uploadUser(MultipartFile headImg, String id, HttpServletRequest request);
    public Map<String,Object> queryBuPager(Integer rows, Integer page);
    void updateuser(User user);
    void uploadUserAliyun(MultipartFile headImg, String id, HttpServletRequest request);

    void uploadUserAliyuns(MultipartFile headImg, String id, HttpServletRequest request);
    List<User> selectmapsexs();
    List<User> selectmapsex();
}
