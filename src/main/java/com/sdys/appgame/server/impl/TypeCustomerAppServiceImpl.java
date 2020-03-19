package com.sdys.appgame.server.impl;

import com.sdys.appgame.entity.TypeCustomerApp;
import com.sdys.appgame.mapper.TypeCustomerAppMapper;
import com.sdys.appgame.server.TypeCustomerAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class TypeCustomerAppServiceImpl implements TypeCustomerAppService {

    @Resource
    private TypeCustomerAppMapper typeCustomerAppMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return typeCustomerAppMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByParam(TypeCustomerApp record) {
        return typeCustomerAppMapper.deleteByParam(record);
    }

    @Override
    public int insert(TypeCustomerApp record) {
        return 0;
    }

    @Override
    public int insertSelective(TypeCustomerApp record) {
        return typeCustomerAppMapper.insertSelective(record);
    }

    @Override
    public TypeCustomerApp selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TypeCustomerApp record) {
        return typeCustomerAppMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TypeCustomerApp record) {
        return 0;
    }

    @Override
    public int selectCountByParam(TypeCustomerApp record) {
        return typeCustomerAppMapper.selectCountByParam(record);
    }

    @Override
    public List<Map<String, String>> selectCustomerType() {
        return typeCustomerAppMapper.selectCustomerType();
    }

    @Override
    public int getMaxIndexItem(TypeCustomerApp record) {
        return typeCustomerAppMapper.getMaxIndexItem(record);
    }

    @Override
    public List<TypeCustomerApp> selectListByPrimary(TypeCustomerApp record) {
        return typeCustomerAppMapper.selectListByPrimary(record);
    }

    @Override
    public int deleteCondition(Map<String, Object> map) {
        return typeCustomerAppMapper.deleteCondition(map);
    }
}
