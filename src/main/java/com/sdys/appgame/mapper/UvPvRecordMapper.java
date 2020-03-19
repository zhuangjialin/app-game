package com.sdys.appgame.mapper;

import com.sdys.appgame.entity.UvPvRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UvPvRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UvPvRecord record);

    int insertSelective(UvPvRecord record);

    UvPvRecord selectByPrimaryKey(Integer id);

    UvPvRecord selectByParams(UvPvRecord record);

    List<UvPvRecord> selectListByParams(UvPvRecord record);

    int updateByPrimaryKeySelective(UvPvRecord record);

    int updateByPrimaryKey(UvPvRecord record);


    int booleanIsExist(UvPvRecord record);
}