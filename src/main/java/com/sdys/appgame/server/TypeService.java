package com.sdys.appgame.server;

import com.sdys.appgame.entity.Type;

import java.util.List;
import java.util.Map;

public interface TypeService {


    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    int deleteType(Integer id);


    Type selectByPrimaryKey(Integer id);

    List<Type> selectByParam(Type record);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Map> findTypesList(Map<String, String> map);
}
