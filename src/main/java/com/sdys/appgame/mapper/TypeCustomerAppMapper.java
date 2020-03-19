package com.sdys.appgame.mapper;

import com.sdys.appgame.entity.TypeCustomerApp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TypeCustomerAppMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByParam(TypeCustomerApp record);

    int insert(TypeCustomerApp record);

    int insertSelective(TypeCustomerApp record);

    TypeCustomerApp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TypeCustomerApp record);

    int updateByPrimaryKey(TypeCustomerApp record);

    int selectCountByParam(TypeCustomerApp record);

    List<Map<String,String>> selectCustomerType ();


    int getMaxIndexItem(TypeCustomerApp record);


    List<TypeCustomerApp> selectListByPrimary(TypeCustomerApp record);


    int deleteCondition(Map<String,Object> map);


}