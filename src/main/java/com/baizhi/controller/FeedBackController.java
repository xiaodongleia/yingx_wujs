package com.baizhi.controller;

import com.baizhi.service.FeedBackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("feedback")
public class FeedBackController {
    @Resource
    FeedBackService feedBackService;


    @RequestMapping("queryByPager")
    /*
     *   page: 当前页   rows: 每页显示多少条
     * */
    @ResponseBody
    public Map<String,Object> queryByPager(Integer page, Integer rows){
        Map<String, Object> map = feedBackService.queryBuPager(rows, page);
        for (String s : map.keySet()) {
            System.out.println("s = " + s);
        }
        return feedBackService.queryBuPager(rows,page);
    }
}
