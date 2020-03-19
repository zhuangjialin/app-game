package com.sdys.appgame.mapper;

import com.sdys.appgame.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    List<Type> selectByParam(Type record);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Map> findTypesList(Map<String, String> map);

    int deleteType(Integer id);


}