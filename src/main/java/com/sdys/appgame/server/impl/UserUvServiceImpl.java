package com.sdys.appgame.server.impl;

import com.sdys.appgame.entity.UserUv;
import com.sdys.appgame.mapper.UserUvMapper;
import com.sdys.appgame.server.UserUvService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserUvServiceImpl implements UserUvService {

    @Resource
    private UserUvMapper userUvMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(UserUv record) {
        return userUvMapper.insert(record);
    }

    @Override
    public int insertSelective(UserUv record) {
        return userUvMapper.insertSelective(record);
    }

    @Override
    public UserUv selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int booleanIsExist(UserUv record) {
        return userUvMapper.booleanIsExist(record);
    }


    @Override
    public int updateByPrimaryKeySelective(UserUv record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserUv record) {
        return 0;
    }
}
