package com.sdys.appgame.server.impl;

import com.sdys.appgame.entity.UserPv;
import com.sdys.appgame.mapper.UserPvMapper;
import com.sdys.appgame.server.UserPvService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserPvServiceImpl implements UserPvService {


    @Resource
    private UserPvMapper userPvMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(UserPv record) {
        return userPvMapper.insert(record);
    }

    @Override
    public int insertSelective(UserPv record) {
        return userPvMapper.insertSelective(record);
    }

    @Override
    public UserPv selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserPv record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserPv record) {
        return 0;
    }
}
