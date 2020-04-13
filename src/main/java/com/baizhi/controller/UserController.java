package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.AliyunSendPhoneUtil;
import com.baizhi.util.HttpClientUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;
    @Resource
    UserMapper userMapper;

    @RequestMapping("edit")
    @ResponseBody
    public String edit(User user,String oper){

        String uid =null;
        if(oper.equals("add")){
            System.out.println(user);
            uid = userService.add(user);
        }

        if(oper.equals("edit")){
        userService.updateuser(user);
        }

        if(oper.equals("del")){
         userMapper.delete(user);
        }
        return uid;
    }

    //文件上传
    @RequestMapping("uploadUser")
    public void uploadUser(MultipartFile headImg, String id, HttpServletRequest request){
        System.out.println("headImg = " + headImg);
        userService.uploadUserAliyuns(headImg,id,request);  //上传到阿里云
    }
    @RequestMapping("queryByPager")
    /*
     *   page: 当前页   rows: 每页显示多少条
     * */
    @ResponseBody
    public Map<String,Object> queryByPager(Integer page, Integer rows){

        return userService.queryBuPager(rows,page);
    }
    //文件上传
    @RequestMapping("updateUser")
    @ResponseBody
    public void updateusers(String id,String status){

        User user = new User();
        user.setId(id);
        user.setStatus(status);
        if (user.getStatus()=="1") {
            user.setId(user.getId());
            user.setStatus("2");
            userService.updateuser(user);
        }else {
            user.setId(user.getId());
            user.setStatus("1");
            userService.updateuser(user);
        }
    }
    @RequestMapping("sendPhoneCode")
    @ResponseBody
    public String sendPhoneCode(String phoneNumbers){
        System.out.println("phone = " + phoneNumbers);
        //获取随机数
        String random = AliyunSendPhoneUtil.getRandom(6);

        System.out.println("存储验证码："+random);

        //发送验证码
        String message = AliyunSendPhoneUtil.sendCode(phoneNumbers, random);

        System.out.println(message);
        return message;
    }
    @RequestMapping("Exportcover")
    @ResponseBody
    public void Exportcover(HttpServletRequest request, HttpServletResponse response)throws Exception{

//        List<User> list = userMapper.selectAll();
//        for (User user : list) {
//            System.out.println("user = " + user);
//        }
//         Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("应学用户表","学 生"),User.class, list);
//         workbook.write(new FileOutputStream(new File("F:/easypoi.xls")));
//        // 释放资源
//        workbook.close();
        //阿里云导出
        response.addHeader("Access-Control-Allow-Origin", "*");
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            byte[] image = HttpClientUtil.getImageFromNetByUrl(user.getHeadImg());
            user.setImage(image);
        }

        String fileName = "测试.xls";
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("应学App用户信息","用户信息表"), User.class, users);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // HSSFWorkbook sheets = new HSSFWorkbook();
        //ServletOutputStream outputStream = response.getOutputStream();
        //String encode = URLEncoder.encode("用户表.xls", "UTF-8");
        //response.setHeader("content-disposition","attachment;filename=" + encode);

        //本地导出
        /*//创建日期格式对象
        HSSFDataFormat dataFormat = sheets.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        //创建样式对象
        HSSFCellStyle cellStyle = sheets.createCellStyle();
        //设置好样式格式对象
        cellStyle.setDataFormat(format);*/
        /*List<User> users = userService.shouAll();

        String path = request.getSession().getServletContext().getRealPath("/upload/photo");
        System.out.println(path+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111");
        for (User user : users) {
            user.setHeadImg(path+"/"+user.getHeadImg());
        }

        Workbook workbook = ExcelExportUtil.exportBigExcel(new ExportParams("应学App用户信息","用户信息表"),User.class,users);
        //workbook.write(response.getOutputStream());
        workbook.write(new FileOutputStream(new File("F://easyPoi-user.xls")));*/

    }
}
