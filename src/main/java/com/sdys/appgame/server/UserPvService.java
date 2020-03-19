package com.sdys.appgame.server;

import com.sdys.appgame.entity.UserPv;

public interface UserPvService {

    int deleteByPrimaryKey(Integer id);

    int insert(UserPv record);

    int insertSelective(UserPv record);

    UserPv selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPv record);

    int updateByPrimaryKey(UserPv record);
}
