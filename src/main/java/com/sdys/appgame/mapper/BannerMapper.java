package com.sdys.appgame.mapper;

import com.sdys.appgame.entity.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer id);

    List<Banner> selectByparam(Banner record);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);


    List<Map> getBanerList(Banner record);

    int deleteByParms(Banner record);




}