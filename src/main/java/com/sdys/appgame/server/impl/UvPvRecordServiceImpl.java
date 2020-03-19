package com.sdys.appgame.server.impl;

import com.sdys.appgame.controller.AdminController;
import com.sdys.appgame.entity.UserPv;
import com.sdys.appgame.entity.UserUv;
import com.sdys.appgame.entity.UvPvRecord;
import com.sdys.appgame.mapper.UserPvMapper;
import com.sdys.appgame.mapper.UserUvMapper;
import com.sdys.appgame.mapper.UvPvRecordMapper;
import com.sdys.appgame.server.UvPvRecordService;
import com.sdys.appgame.utils.CusAccessObjectUtil;
import com.sdys.appgame.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UvPvRecordServiceImpl implements UvPvRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UvPvRecordServiceImpl.class);


    @Resource
    private UvPvRecordMapper uvPvRecordMapper;


    @Resource
    private UserPvMapper userPvMapper;

    @Resource
    private UserUvMapper userUvMapper;



    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(UvPvRecord record) {
        return 0;
    }

    @Override
    public int insertSelective(UvPvRecord record) {
        return uvPvRecordMapper.insertSelective(record);
    }

    @Override
    public UvPvRecord selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UvPvRecord record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UvPvRecord record) {
        return 0;
    }


    @Override
    @Transactional
    public void addUserUv(String appId,HttpServletRequest request){
        String createTime = DateUtils.getDateByString();
        String ip = CusAccessObjectUtil.getIpAddress(request);
        LOGGER.info("*********addUserUv ip*********{}",ip);

        if(userUvMapper.booleanIsExist(new UserUv(appId,ip,createTime)) ==0){
            //如果UV表今天没有该ip的数据则插入UV表，有的话则不插入
            userUvMapper.insertSelective(new UserUv(appId,ip,createTime));
            //插入PV表，点击一次则插入一次
            userPvMapper.insertSelective(new UserPv(appId,ip,createTime));

            //查询PV跟UV关系表，查看是否有今天的这个小程序的appid记录，如果有的话则更新，没有的话则插入
            UvPvRecord uvPvRecord = new UvPvRecord();
            uvPvRecord.setAppId(appId);
            uvPvRecord.setCreateTime(createTime);
            UvPvRecord orginUvPvRecord  = uvPvRecordMapper.selectByParams(uvPvRecord);
            LOGGER.info("*********addUserUv orginUvPvRecord*********{}",orginUvPvRecord);
            if(StringUtils.isEmpty(orginUvPvRecord)){
                UvPvRecord uvPvRecord1 = new UvPvRecord();
                uvPvRecord1.setPv(1);
                uvPvRecord1.setUv(1);
                uvPvRecord1.setAppId(appId);
                uvPvRecord1.setCreateTime(createTime);
                uvPvRecord1.setBeginTime(new Date());
                uvPvRecordMapper.insertSelective(uvPvRecord1);
            }else{
                orginUvPvRecord.setUv(orginUvPvRecord.getUv()+1);
                orginUvPvRecord.setUpdateTime(new Date());
                uvPvRecordMapper.updateByPrimaryKeySelective(orginUvPvRecord);
            }
        }

    }

    @Override
    @Transactional
    public void addUserPv(String appId, HttpServletRequest request){

        String createTime = DateUtils.getDateByString();
        String ip = CusAccessObjectUtil.getIpAddress(request);
        userPvMapper.insertSelective(new UserPv(appId,ip,createTime));
        LOGGER.info("*********addUserPv ip*********{}",ip);

        UvPvRecord uvPvRecord = new UvPvRecord();
        uvPvRecord.setAppId(appId);
        uvPvRecord.setCreateTime(createTime);
        LOGGER.info("*********addUserPv uvPvRecord*********{}",uvPvRecord.toString());

        UvPvRecord orginUvPvRecord  = uvPvRecordMapper.selectByParams(uvPvRecord);
        LOGGER.info("*********addUserPv orginUvPvRecord*********{}",orginUvPvRecord);
        if(StringUtils.isEmpty(orginUvPvRecord)){
            //不存在则新增
            UvPvRecord uvPvRecord1 = new UvPvRecord();
            uvPvRecord1.setPv(1);
            uvPvRecord1.setUv(0);
            uvPvRecord1.setAppId(appId);
            uvPvRecord1.setCreateTime(createTime);
            uvPvRecord1.setBeginTime(new Date());
            uvPvRecordMapper.insertSelective(uvPvRecord1);
        }else{
            //存在则更新
            orginUvPvRecord.setPv(orginUvPvRecord.getPv()+1);
            orginUvPvRecord.setUpdateTime(new Date());
            uvPvRecordMapper.updateByPrimaryKeySelective(orginUvPvRecord);
        }
    }

    @Override
    public void addPv(Map<String, String> map) {
        commonAddPv(map);
    }

    @Override
    public void addUv(Map<String, String> map) {
        UvPvRecord uvPvRecord = commonAddPv(map);
        String appid = map.get("appId");
        String openid = map.get("openid");
        UserUv userUv = new UserUv(appid,openid,DateUtils.getDateByString());
        int count = userUvMapper.booleanIsExist(userUv);
        if(count ==0){
            userUv.setBeginTime(new Date());
            userUvMapper.insertSelective(userUv);
            UvPvRecord editUvRecord = new UvPvRecord();
            int uv = StringUtils.isEmpty(uvPvRecord.getUv())? 0:uvPvRecord.getUv();
            LOGGER.info("*********addUv *********editUvRecord = {}",uvPvRecord);
            editUvRecord.setUv(uv+1);
            editUvRecord.setId(uvPvRecord.getId());
            LOGGER.info("*********addUv *********editUvRecord = {}",editUvRecord);
            uvPvRecordMapper.updateByPrimaryKeySelective(editUvRecord);
        }
    }

    private UvPvRecord commonAddPv(Map<String, String> map){
        String appId = map.get("appId");
        String createTime = DateUtils.getDateByString();
        String ip = map.get("id");
        userPvMapper.insertSelective(new UserPv(appId,ip,createTime));
        UvPvRecord uvPvRecord = new UvPvRecord();
        uvPvRecord.setAppId(appId);
        uvPvRecord.setCreateTime(createTime);
        List<UvPvRecord> uvPvRecordList  = uvPvRecordMapper.selectListByParams(uvPvRecord);
        if(uvPvRecordList.size()>0){
            //存在则更新
            UvPvRecord eidtPvRecord = new UvPvRecord();
            eidtPvRecord.setId(uvPvRecordList.get(0).getId());
            eidtPvRecord.setPv(uvPvRecordList.get(0).getPv()+1);
            eidtPvRecord.setUpdateTime(new Date());
            uvPvRecordMapper.updateByPrimaryKeySelective(eidtPvRecord);
            return uvPvRecordList.get(0);
        }else{
            //不存在则添加
            uvPvRecord.setBeginTime(new Date());
            uvPvRecord.setPv(1);
            uvPvRecord.setUv(0);
            uvPvRecordMapper.insertSelective(uvPvRecord);
            return uvPvRecord;
        }
    }
}
