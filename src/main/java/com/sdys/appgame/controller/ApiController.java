package com.sdys.appgame.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sdys.appgame.entity.*;
import com.sdys.appgame.server.*;
import com.sdys.appgame.utils.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/index")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);


    @Resource
    private CustomerAppService customerAppService;

    @Resource
    private TypeService typeService;

    @Resource
    private UvPvRecordService uvPvRecordService;

    @Resource
    private ConfigService configService;

    @Value("${xcxShare.shareTitle}")
    private String shareTitle;


    @Value("${xcxShare.shareImg}")
    private String shareImg;

    //获取首页小程序列表
    @GetMapping("/getGamesList")
    public Object selectByPrimaryKey(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> xcxShare = new HashMap<>();
        Type searchType = new Type();
        searchType.setIsHide(1);
        List<Type> typeList = typeService.selectByParam(searchType);
        List<CustomerApp> customerAppList =  customerAppService.selectByParams(new CustomerApp());
        for (Type type : typeList) {
            List<CustomerApp> list1 = new ArrayList<>();
            for (CustomerApp customerApp : customerAppList) {
                String[] typeIds = customerApp.getTypeId().split(",");
                if(Arrays.asList(typeIds).contains(type.getId().toString())){
                    list1.add(customerApp);
                }
            }
            JSON.toJSONString(list1, SerializerFeature.DisableCircularReferenceDetect);
            type.setLists(list1);
        }


        result.put("msg","");
        result.put("code",0);
        xcxShare.put("shareTitle",this.shareTitle);
        xcxShare.put("shareImg",this.shareImg);
        data.put("banner",customerAppService.selectBannerList());
        data.put("xcxShare",xcxShare);
        data.put("xcxList",JSON.toJSON(typeList));
        LOGGER.info("*********** selectByAppId *********** = {}",typeList.get(1).toString());


        result.put("data",data);
        return result;
    }



    //获取首页小程序列表
    @GetMapping("/getGame/{Id}")
    public Object selectByAppId(@PathVariable String Id){

        LOGGER.info("*********** selectByAppId *********** = {}",Id);
        Map<String,Object> result = new HashMap<>();
        List<Type> typeList = typeService.selectByParam(new Type());
        CustomerApp customerAppList =  customerAppService.selectByPrimaryKey(Integer.valueOf(Id));
        return customerAppList;
    }



    @GetMapping("/addUserUv/{appId}")
    public Object addUserUv(@PathVariable String appId,HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        LOGGER.info("*********** addUserUv *********** appId= {}",appId);
        uvPvRecordService.addUserUv(appId,request);
        result.put("msg","");
        result.put("code","0");
        return result;
    }




    @GetMapping("/addUserPv/{appId}")
    public Object addUserPv(@PathVariable String appId, HttpServletRequest request){

        Map<String,Object> result = new HashMap<>();
        LOGGER.info("*********** addUserPv *********** appId= {}",appId);
        uvPvRecordService.addUserPv(appId,request);
        result.put("code","0");
        result.put("msg","");
        return result;

    }


    //判断是否展示骗审页面
    @GetMapping("/isNormalShow/{appId}")
    public Object isNormalShow(@PathVariable String appId){
        return configService.selectByParam(new Config(appId));
    }





    @GetMapping("/add_uv")
    public void add_uv(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        String ip = request.getParameter("ip");
        String appId = request.getParameter("appid");
        String openid = request.getParameter("openid");
        map.put("openid",openid);
        map.put("ip",ip);
        map.put("appId",appId);
        LOGGER.info("*********** add_uv *********** appId= {}",map.toString());
        uvPvRecordService.addUv(map);
    }





    @GetMapping("/add_pv")
    public void add_pv(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        String appId = request.getParameter("appid");
        String ip = request.getParameter("ip");
        String openid = request.getParameter("openid");
        map.put("appId",appId);
        map.put("ip",ip);
        map.put("ip",openid);
        LOGGER.info("*********** add_pv *********** appId= {}",map.toString());
        uvPvRecordService.addPv(map);
    }




    /*********************************************************************/

    //获取首页小程序列表
    @GetMapping("/find_game_list/{appId}")
    public Object findGameList(@PathVariable String appId){
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> xcxShare = new HashMap<>();
        Config config = configService.selectByParam(new Config(appId));
        if(!StringUtils.isEmpty(config)){
            result.put("msg","数据库不存在这笔appid");
            result.put("code",500);
        }
        if(config.getIsShow() ==0){
            result.put("msg","审核状态");
            result.put("code",400);
        }else{
            Type searchType = new Type();
            searchType.setIsHide(1);
            List<Type> typeList = typeService.selectByParam(searchType);
            List<CustomerApp> customerAppList =  customerAppService.selectByParams(new CustomerApp());
            for (Type type : typeList) {
                List<CustomerApp> customerApps = new ArrayList<>();
                for (CustomerApp customerApp : customerAppList) {
                    String typeIds = customerApp.getTypeId();
                    if(!StringUtils.isEmpty(typeIds)){
                        List<String> typeArrayList= Arrays.asList(typeIds.split(","));
                        String typeIsStr = type.getId()+"";
                        if(typeArrayList.contains(typeIsStr)){
                            customerApps.add(customerApp);
                        }
                    }
                }
                type.setLists(customerApps);
            }
            result.put("code",0);
            result.put("msg","");
            xcxShare.put("shareImg",this.shareImg);
            xcxShare.put("shareTitle",this.shareTitle);
            data.put("banner",customerAppService.selectBannerList());
            data.put("xcxShare",xcxShare);
            data.put("xcxList",JSON.toJSON(typeList));
            result.put("data",data);
        }
        return result;
    }





    //判断是否展示骗审页面
    @GetMapping("/check_box")
    public Object checkBox(HttpServletRequest request) throws Exception {

        String appId = request.getParameter("appId");
        String id = request.getParameter("id");
        String code = request.getParameter("code");

        Map<String,Object> result = new HashMap<>();
        LOGGER.info("*********** checkBox *********** appId= {},id={}",appId,id);
        Config config = configService.selectByParam(new Config(appId));
        if(!"undefined".equals(id)){
            if(StringUtils.isEmpty(config)){
                //骗审页面
                result.put("code","500");
                result.put("isShow",0);
                result.put("msg","小程序appid不存在");
            }else if(config.getIsShow() == 1){
                //获取列表
                CustomerApp customerApp =  customerAppService.selectByPrimaryKey(Integer.valueOf(id));

                String openid = WeChatUtil.getOpenid(code,customerApp.getAppId(),customerApp.getSecretKey());
                LOGGER.info("*********** openid *********** openid= {}",openid);
                result.put("code","200");
                result.put("msg","查询成功");
                result.put("isShow",1);
                result.put("openid",openid);
                result.put("unitId",config.getUnitId());
                result.put("data",customerApp);
            }else{
                //骗审页面
                result.put("code","400");
                result.put("isShow",0);
                result.put("msg","小程序处于审核状态");
            }
        }else{
            //骗审页面
            result.put("code","400");
            result.put("isShow",0);
            result.put("msg","小程序处于审核状态");
        }
        return result;
    }

}
