package com.sdys.appgame.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeChatUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatUtil.class);


    public static String getOpenid(String code,String wxspAppid,String wxspSecret) throws Exception{

        Map<String,Object> map = new HashMap<String,Object>();
        //code = "081ZExyD0qnP4j2LV5yD0hFLyD0ZExyK";
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            System.out.println("map1:" + map);
        }
/*        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wx48e4159777a0fcdb";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "308b8d66bfcf9f4e85de16ea18657faa";
        //授权（必填）*/
        String grant_type = "authorization_code";
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        String openid = (String) json.get("openid");
        LOGGER.info("openid = {}",openid);
        return openid;
    }



    public static String getAccessToken(String appId,String secret){
        String param = "grant_type=client_credential&appid="+appId+"&secret="+secret;
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String json= HttpRequest.sendGet(url,param);
        JSONObject obj = (JSONObject) JSON.parse(json);
        LOGGER.info("obj = {}",obj.toJSONString());
        return String.valueOf(obj.get("access_token"));
    }


    public static List<Map<String,Object>> getDailyVisitTrend(String appId, String secret,String beginDate,String endDate){
        String ACCESS_TOKEN = getAccessToken(appId,secret);
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend?access_token="+ACCESS_TOKEN;
        JSONObject params = new JSONObject();
        params.put("begin_date",beginDate);
        params.put("end_date",endDate);
        String strArr = HttpRequest.doPostJson(url,params.toJSONString());
        JSONObject obj = (JSONObject) JSON.parse(strArr);
        LOGGER.info("obj = {}",obj.toString());
        List<Map<String,Object>> listObjectSec = new ArrayList<>();
        if(StringUtils.isEmpty(obj.get("errcode"))){
            listObjectSec =(List<Map<String, Object>>) obj.get("list");
        }
        LOGGER.info("obj = {}",listObjectSec.toString());
        return listObjectSec;
    }

/*

    public static void main(String[] args) {

        getDailyVisitTrend("wx20445af1a41fb48c","7d0a147e56b350803dafd5dbaa14785c","20200311","20200311");

    }
*/

}
