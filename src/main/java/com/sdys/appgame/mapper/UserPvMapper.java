package com.sdys.appgame.mapper;

import com.sdys.appgame.entity.UserPv;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPvMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPv record);

    int insertSelective(UserPv record);

    UserPv selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPv record);

    int updateByPrimaryKey(UserPv record);

    int isExistUv(UserPv record);


}