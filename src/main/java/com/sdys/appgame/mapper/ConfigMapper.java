package com.sdys.appgame.mapper;

import com.sdys.appgame.entity.Config;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    Config selectByParam(Config record);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    List<Config> getConfigList(Config record);



    List<Config> findConfigByVisit(Config record);

    void batchUpdate(Integer isShow);



}