package com.sdys.appgame.mapper;

import com.sdys.appgame.entity.DailyVisitTrend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DailyVisitTrendMapper {
    int insert(DailyVisitTrend record);

    int insertSelective(DailyVisitTrend record);

    List<DailyVisitTrend> findByParams(DailyVisitTrend record);



    Map<String,String> getAllCountData(Map<String,String> record);

    List<Map<String,String>> findDailyVisitTrend(Map<String,String> record);


    DailyVisitTrend findById(Integer id);


    int updateByPrimaryKeySelective(DailyVisitTrend record);

    int booleanIsExist(DailyVisitTrend record);



}