package com.baizhi.controller;

import com.baizhi.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("log")
public class LogController {
    @Resource
    LogService logService;
    @RequestMapping("queryByPager")
    /*
     *   page: 当前页   rows: 每页显示多少条
     * */
    @ResponseBody
    public Map<String,Object> queryByPager(Integer page, Integer rows){

        return logService.queryBuPager(rows,page);
    }
}
