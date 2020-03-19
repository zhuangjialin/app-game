package com.sdys.appgame.server;

import com.sdys.appgame.entity.Config;

import java.util.List;

public interface ConfigService {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    Config selectByParam(Config record);


    List<Config> getConfigList(Config record);

    List<Config> findConfigList(Config record);


    List<Config> findConfigByVisit(Config record);

    void batchUpdate(Integer isShow);


}
