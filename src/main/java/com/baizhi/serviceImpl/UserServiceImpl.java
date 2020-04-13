package com.baizhi.serviceImpl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.AddLog;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.service.UserService;
import com.baizhi.util.AliyunOssUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @AddLog(value = "添加用户")
    @Override
    public String add(User user) {
        String uid = UUID.randomUUID().toString();
        user.setId(uid);
        user.setStatus("1");
        user.setCreatDate(new Date());
        userMapper.insert(user);
        return uid;
    }

    @AddCache
    @Override
    public Map<String, Object> queryBuPager(Integer rows, Integer page) {
        /*
         * page: 当前页
         * rows: 数据库中的数据
         * total: 总页数
         * records: 总条数
         * */
        /* 起始下标 */
        Integer start = (page-1)*rows;
        List<User> list = userMapper.selectByPager(start, rows);
        Integer records = userMapper.selectRecords();
        Integer total = records%rows==0? records/rows:records/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",total);
        map.put("records",records);
        return map;

    }

    @Override
    public void updateuser(User user) {
        userMapper.updateuser(user);
    }

    @Override
    public void uploadUserAliyun(MultipartFile headImg, String id, HttpServletRequest request) {

        //将文件转为byte数组
        byte[] bytes =null;
        try {
            bytes = headImg.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取文件名
        String filename = headImg.getOriginalFilename();
        String newName=new Date().getTime()+"-"+filename;


        //1.文件上传至阿里云

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FcoTVQPRZccnTpBRqbp";
        String accessKeySecret = "fhvY6MyGL6RCEvLLr3USm3zPo7I6Dp";
        String bucket="yingx-wujs";   //存储空间名
        String fileName=newName;  //指定上传文件名  可以指定上传目录

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 上传Byte数组。
        ossClient.putObject(bucket, fileName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();


        //2.图片信息的修改
        //修改的条件
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);

        User user = new User();
        user.setHeadImg("https://yingx-wujs.oss-cn-beijing.aliyuncs.com/"+newName);  //设置修改的结果   网络路径
        //https://yingx-186.oss-cn-beijing.aliyuncs.com/1585641490828-9.jpg

        //修改
        userMapper.updateByExampleSelective(user,example);

    }

    @Override
    public void uploadUserAliyuns(MultipartFile headImg, String id, HttpServletRequest request) {
        //获取文件名
        String filename = headImg.getOriginalFilename();
        String newName=new Date().getTime()+"-"+filename;

        //1.文件上传至阿里云
        AliyunOssUtil.uploadFileBytes("yingx-wujs",headImg,newName);

        //截取视频第一帧
        //上传封面

        //2.图片信息的修改
        //修改的条件
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);

        User user = new User();
        user.setHeadImg("https://yingx-wujs.oss-cn-beijing.aliyuncs.com/"+newName);  //设置修改的结果   网络路径
        //https://yingx-186.oss-cn-beijing.aliyuncs.com/1585641490828-9.jpg

        //修改
        userMapper.updateByExampleSelective(user,example);

    }
//n
    @Override
    public List<User> selectmapsexs() {
        List<User> selectmapsexs = userMapper.selectmapsexs();
        return selectmapsexs;
    }
//nan
    @Override
    public List<User> selectmapsex() {
        List<User> selectmapsex = userMapper.selectmapsex();
        return selectmapsex;
    }

    @Override
    public void uploadUser(MultipartFile headImg, String id, HttpServletRequest request) {
        //1.根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");

        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }

        //2获取文件名
        String filename = headImg.getOriginalFilename();

        String newName=new Date().getTime()+"-"+filename;

        try {
            //3.文件上传
            headImg.transferTo(new File(realPath,newName));

            //4.图片修改
            //修改的条件
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(id);

            User user = new User();
            user.setHeadImg(newName); //设置修改的结果

            //修改
            userMapper.updateByExampleSelective(user,example);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
