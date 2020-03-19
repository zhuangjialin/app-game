package com.sdys.appgame.server;

import com.sdys.appgame.entity.UvPvRecord;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface UvPvRecordService {


    int deleteByPrimaryKey(Integer id);

    int insert(UvPvRecord record);

    int insertSelective(UvPvRecord record);

    UvPvRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UvPvRecord record);

    int updateByPrimaryKey(UvPvRecord record);


    void addUserUv(String addUserPv,HttpServletRequest request);

    void addUserPv(String addUserPv, HttpServletRequest request);


    void addPv(Map<String,String> map);

    void addUv(Map<String,String> map);

}
