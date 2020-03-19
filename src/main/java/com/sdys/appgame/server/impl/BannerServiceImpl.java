package com.sdys.appgame.server.impl;

import com.sdys.appgame.entity.Banner;
import com.sdys.appgame.mapper.BannerMapper;
import com.sdys.appgame.server.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {


    @Resource
    private BannerMapper bannerMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Banner record) {
        return 0;
    }

    @Override
    public int insertSelective(Banner record) {
        return bannerMapper.insertSelective(record);
    }

    @Override
    public Banner selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<Banner> selectByparam(Banner record) {
        return bannerMapper.selectByparam(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Banner record) {
        return bannerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Banner record) {
        return 0;
    }

    @Override
    public List<Map> getBanerList(Banner record) {
        return bannerMapper.getBanerList(record);
    }

    @Override
    public int deleteByParms(Banner record) {
        return bannerMapper.deleteByParms(record);
    }
}
