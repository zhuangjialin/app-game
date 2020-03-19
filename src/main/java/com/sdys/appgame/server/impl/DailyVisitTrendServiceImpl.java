package com.sdys.appgame.server.impl;

import com.sdys.appgame.entity.DailyVisitTrend;
import com.sdys.appgame.mapper.DailyVisitTrendMapper;
import com.sdys.appgame.server.DailyVisitTrendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class DailyVisitTrendServiceImpl implements DailyVisitTrendService {
    @Resource
    private DailyVisitTrendMapper dailyVisitTrendMapper;

    @Override
    public int insert(DailyVisitTrend record) {
        return dailyVisitTrendMapper.insert(record);
    }

    @Override
    public int insertSelective(DailyVisitTrend record) {
        return dailyVisitTrendMapper.insertSelective(record);
    }

    @Override
    public List<DailyVisitTrend> findByParams(DailyVisitTrend record) {
        return null;
    }

    @Override
    public List<Map<String, String>> findDailyVisitTrend(Map<String, String> record) {
        return dailyVisitTrendMapper.findDailyVisitTrend(record);
    }

    @Override
    public Map<String, String> getAllCountData(Map<String, String> record) {
        return dailyVisitTrendMapper.getAllCountData(record);
    }

    @Override
    public DailyVisitTrend findById(Integer id) {
        return dailyVisitTrendMapper.findById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DailyVisitTrend record) {
        return dailyVisitTrendMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int booleanIsExist(DailyVisitTrend record) {
        return dailyVisitTrendMapper.booleanIsExist(record);
    }
}
