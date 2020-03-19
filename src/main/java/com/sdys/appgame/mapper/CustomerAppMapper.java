package com.sdys.appgame.mapper;

import com.sdys.appgame.entity.CustomerApp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerAppMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerApp record);

    int insertSelective(CustomerApp record);

    CustomerApp selectByPrimaryKey(Integer id);

    CustomerApp findByParams(CustomerApp record);

    int updateByPrimaryKeySelective(CustomerApp record);

    int updateByPrimaryKey(CustomerApp record);

    List<CustomerApp> selectByParams(CustomerApp record);

    List<CustomerApp> selectBannerList();

    List<CustomerApp> getCustomerAppList(CustomerApp record);

    List<CustomerApp> findByPrimaryKey(CustomerApp record);


    List<Map> advertisingStatistics(Map<String,Object> params);






}