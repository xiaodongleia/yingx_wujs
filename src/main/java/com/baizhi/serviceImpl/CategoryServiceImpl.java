package com.baizhi.serviceImpl;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.AddLog;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;
import com.baizhi.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;
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
        List<Category> list = categoryMapper.selectByPager(start, rows);
        Integer records = categoryMapper.selectRecords();
        Integer total = records%rows==0? records/rows:records/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",total);
        map.put("records",records);
        return map;
    }
    @AddCache
    @Override
    public Map<String, Object> queryBuPager1(Integer rows, Integer page,String uid) {
        /*
         * page: 当前页
         * rows: 数据库中的数据
         * total: 总页数
         * records: 总条数
         * */
        /* 起始下标 */
        Integer start = (page-1)*rows;
        List<Category> list = categoryMapper.selectByPager1(start, rows,uid);
        Integer records = categoryMapper.selectRecords1(uid);
        Integer total = records%rows==0? records/rows:records/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",total);
        map.put("records",records);
        return map;
    }
    @AddLog(value = "添加类别")
    @Override
    public void insert(Category category) {

        categoryMapper.insert(category);
    }
    @AddLog(value = "删除类别")
    @Override
    public HashMap<String, Object> delete(Category category) {
        HashMap<String, Object> map = new HashMap<>();
        //根据类别对象查询类别信息   id
        Category cate = categoryMapper.selectOne(category);

        //判断删除的是一级类别还是二级类别
        if (cate.getLevels() == "1"){
            //一级类别  判断是否有二级类别   二级类别数量
            CategoryExample example = new CategoryExample();
            example.createCriteria().andParentIdEqualTo(category.getId());
            int count = categoryMapper.selectCountByExample(example);
            if(count==0){
                //没有   直接删除
                categoryMapper.deleteByPrimaryKey(category);
                map.put("status","200");
                map.put("message","删除成功");
            }else{
                //有二级类别   返回提示信息  不能删除
                map.put("status","400");
                map.put("message","删除失败，该类别下有子类别");
            }
        }else{
            //二级类别  是否有视频
            //有   不能删除  提示信息
            //没有 直接删除
            categoryMapper.deleteByPrimaryKey(category);
            map.put("status","200");
            map.put("message","删除成功");
        }
        return map;
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public List<Category> queryAllCategory() {
        List<Category> categories = categoryMapper.queryAllCategory();
        return categories;
    }


}
