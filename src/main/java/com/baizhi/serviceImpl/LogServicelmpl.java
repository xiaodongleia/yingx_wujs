package com.baizhi.serviceImpl;

import com.baizhi.dao.LogDao;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LogServicelmpl implements LogService {
    @Resource
    LogDao logDao;

    @Override
    public void insertlog(Log log) {
        logDao.insertlog(log);
    }

    @Override
    public Map<String, Object> queryBuPager(Integer rows, Integer page) {
        Integer start = (page-1)*rows;
        List<Log> list = logDao.selectByPager(start, rows);
        Integer records = logDao.selectRecords();
        Integer total = records%rows==0? records/rows:records/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",total);
        map.put("records",records);
        return map;
    }
}
