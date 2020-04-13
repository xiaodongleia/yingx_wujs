package com.baizhi.app;

import com.baizhi.common.CommonResult;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunSendPhoneUtil;
import com.baizhi.vo.VideoVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppInterfaceController {

    @Resource
    VideoService videoService;
    @Resource
    CategoryService categoryService;

    /*发送验证码的接口*/
    @RequestMapping("getPhoneCode")
    public CommonResult getPhoneCodes(String phone){
        //生成随机数
        String random = AliyunSendPhoneUtil.getRandom(6);
        System.out.println("存储验证码："+random);
        //发送验证码
        String message = AliyunSendPhoneUtil.sendCode(phone, random);
        System.out.println("验证码发送："+message);
        if (message.equals("发送成功")){
            return new CommonResult().success("100","发送成功",phone);
        }else{
            return new CommonResult().failed("发送失败:"+message,null);
        }
    }

    /*
    * 首页查询视频信息接口
    * */
    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime(){
        try {
            //查询数据
            List<VideoVo> videoVos = videoService.queryByReleaseTime();
            System.out.println("videoVos = " + videoVos);
            return new CommonResult().success(videoVos);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed();
        }
    }
    @RequestMapping("queryAllCategory")
    public CommonResult queryAllCategory(){
        try {

            List<Category> categories = categoryService.queryAllCategory();
            System.out.println("categories = " + categories);
            return new CommonResult().success(categories);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed();
        }
    }

}
