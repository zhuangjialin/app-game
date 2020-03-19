package com.sdys.appgame.server.impl;

import com.github.pagehelper.PageHelper;
import com.sdys.appgame.controller.ConfigController;
import com.sdys.appgame.entity.Config;
import com.sdys.appgame.mapper.ConfigMapper;
import com.sdys.appgame.server.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigMapper configMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceImpl.class);


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return configMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Config record) {
        return 0;
    }

    @Override
    public int insertSelective(Config record) {
        return configMapper.insertSelective(record);
    }

    @Override
    public Config selectByPrimaryKey(Integer id) {
        return configMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Config record) {
        return configMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Config record) {
        return 0;
    }

    @Override
    public Config selectByParam(Config record) {
        return configMapper.selectByParam(record);
    }

    @Override
    public List<Config> getConfigList(Config record) {

        return configMapper.getConfigList(record);
    }

    @Override
    public List<Config> findConfigList(Config record) {
        return configMapper.getConfigList(record);
    }

    @Override
    public List<Config> findConfigByVisit(Config record) {
        return configMapper.findConfigByVisit(record);
    }

    @Override
    public void batchUpdate(Integer isShow) {
        configMapper.batchUpdate(isShow);
    }
}
