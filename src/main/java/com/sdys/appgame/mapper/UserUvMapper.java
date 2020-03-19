package com.sdys.appgame.mapper;

import com.sdys.appgame.entity.UserUv;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserUvMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserUv record);

    int insertSelective(UserUv record);

    UserUv selectByPrimaryKey(Integer id);

    int booleanIsExist(UserUv record);

    int updateByPrimaryKeySelective(UserUv record);

    int updateByPrimaryKey(UserUv record);
}