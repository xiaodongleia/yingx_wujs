package com.baizhi.serviceImpl;

import com.baizhi.dao.FeedBackMapper;
import com.baizhi.entity.FeedBack;
import com.baizhi.service.FeedBackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FeedBackServiceImpl implements FeedBackService {
    @Resource
    FeedBackMapper feedBackMapper;

    @Override
    public Map<String, Object> queryBuPager(Integer rows, Integer page) {
        Integer start = (page-1)*rows;
        List<FeedBack> list = feedBackMapper.selectByPager(start, rows);
        Integer records = feedBackMapper.selectRecords();
        Integer total = records%rows==0? records/rows:records/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",total);
        map.put("records",records);
        return map;
    }
}
