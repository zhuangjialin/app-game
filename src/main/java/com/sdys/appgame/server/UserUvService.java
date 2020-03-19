package com.sdys.appgame.server;

import com.sdys.appgame.entity.UserUv;

public interface UserUvService {

    int deleteByPrimaryKey(Integer id);

    int insert(UserUv record);

    int insertSelective(UserUv record);

    UserUv selectByPrimaryKey(Integer id);

    int booleanIsExist(UserUv record);

    int updateByPrimaryKeySelective(UserUv record);

    int updateByPrimaryKey(UserUv record);


}
