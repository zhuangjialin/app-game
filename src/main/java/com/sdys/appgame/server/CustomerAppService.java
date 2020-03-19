package com.sdys.appgame.server;

import com.sdys.appgame.entity.CustomerApp;

import java.util.List;
import java.util.Map;

public interface CustomerAppService {

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerApp record);

    int insertSelective(CustomerApp record);

    CustomerApp selectByPrimaryKey(Integer id);

    CustomerApp findByParams(CustomerApp record);

    List<CustomerApp> selectByParams(CustomerApp record);

    int updateByPrimaryKeySelective(CustomerApp record);

    int updateByPrimaryKey(CustomerApp record);

    List<CustomerApp> selectBannerList();

    List<CustomerApp> getCustomerAppList(CustomerApp record);

    List<CustomerApp> findByPrimaryKey(CustomerApp record);

    List<Map> advertisingStatistics(Map<String,Object> params);


}
