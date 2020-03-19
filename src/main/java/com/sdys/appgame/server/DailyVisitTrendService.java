package com.sdys.appgame.server;

import com.sdys.appgame.entity.DailyVisitTrend;

import java.util.List;
import java.util.Map;

public interface DailyVisitTrendService {

    int insert(DailyVisitTrend record);

    int insertSelective(DailyVisitTrend record);

    List<DailyVisitTrend> findByParams(DailyVisitTrend record);


    List<Map<String,String>> findDailyVisitTrend(Map<String, String> record);


    Map<String,String> getAllCountData(Map<String,String> record);


    DailyVisitTrend findById(Integer id);


    int updateByPrimaryKeySelective(DailyVisitTrend record);


    int booleanIsExist(DailyVisitTrend record);
}
