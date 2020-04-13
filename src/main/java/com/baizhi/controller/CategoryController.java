package com.baizhi.controller;

import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Resource
    CategoryService categoryService;


//一级展示分页
    @RequestMapping("queryByPager")
    /*
     *   page: 当前页   rows: 每页显示多少条
     * */
    @ResponseBody
    public Map<String, Object> queryByPager(Integer page, Integer rows) {
        System.out.println("rows = " + rows);
        Map<String, Object> stringObjectMap = categoryService.queryBuPager(rows, page);
        for (String s : stringObjectMap.keySet()) {
            System.out.println("stringObjectMap = " + stringObjectMap);
        }
        return categoryService.queryBuPager(rows, page);
    }
//二级展示分页
    @RequestMapping("query")
    @ResponseBody
    public Map<String, Object> queryByPager1(Integer page, Integer rows, String uid) {
        System.out.println("*****************************************");
        System.out.println("rowId = " + uid);
    Map<String, Object> stringObjectMap = categoryService.queryBuPager1(rows, page,uid);
        for (String s : stringObjectMap.keySet()) {
        System.out.println("stringObjectMap = " + stringObjectMap);
    }
        return categoryService.queryBuPager1(rows,page,uid);
}
    @RequestMapping("edit")
    @ResponseBody
    public HashMap<String, Object> adds(Category category, String id, String oper){

        if ("add".equals(oper)) {

            category.setId(UUID.randomUUID().toString());
            categoryService.insert(category);
        }else if ("del".equals(oper)){
            HashMap<String, Object> map = categoryService.delete(category);
            System.out.println(map.get("message"));
            return map;

        }else if("edit".equals(oper)){
            categoryService.update(category);
        }


        return null;
    }
    @RequestMapping("editm")
    @ResponseBody
    public HashMap<String, Object> addm(Category category, String parentId, String oper){

        if ("add".equals(oper)) {

            category.setId(UUID.randomUUID().toString());
            category.setParentId(parentId);
            categoryService.insert(category);
        }else if ("del".equals(oper)){
            HashMap<String, Object> map = categoryService.delete(category);
            System.out.println(map.get("message"));
            return map;

        }else if("edit".equals(oper)){
            categoryService.update(category);
        }


        return null;
    }
}