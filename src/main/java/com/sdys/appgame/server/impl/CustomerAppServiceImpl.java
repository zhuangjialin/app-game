package com.sdys.appgame.server.impl;

import com.sdys.appgame.entity.CustomerApp;
import com.sdys.appgame.mapper.CustomerAppMapper;
import com.sdys.appgame.server.CustomerAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CustomerAppServiceImpl implements CustomerAppService {


    @Resource
    private CustomerAppMapper customerAppMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return customerAppMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CustomerApp record) {
        return 0;
    }

    @Override
    public int insertSelective(CustomerApp record) {
        return customerAppMapper.insertSelective(record);
    }

    @Override
    public CustomerApp selectByPrimaryKey(Integer id) {
        return customerAppMapper.selectByPrimaryKey(id);
    }

    @Override
    public CustomerApp findByParams(CustomerApp record) {
        return null;
    }

    @Override
    public List<CustomerApp> selectByParams(CustomerApp record) {
        return customerAppMapper.selectByParams(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CustomerApp record) {
        return customerAppMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CustomerApp record) {
        return 0;
    }

    @Override
    public List<CustomerApp> selectBannerList() {
        return customerAppMapper.selectBannerList();
    }

    @Override
    public List<CustomerApp> getCustomerAppList(CustomerApp record) {
        return customerAppMapper.getCustomerAppList(record);
    }

    @Override
    public List<CustomerApp> findByPrimaryKey(CustomerApp record) {
        return customerAppMapper.findByPrimaryKey(record);
    }

    @Override
    public List<Map> advertisingStatistics(Map<String, Object> params) {
        return customerAppMapper.advertisingStatistics(params);
    }
}
