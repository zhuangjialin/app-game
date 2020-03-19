package com.sdys.appgame.controller;


import com.alibaba.fastjson.JSONArray;
import com.sdys.appgame.entity.Banner;
import com.sdys.appgame.entity.CustomerApp;
import com.sdys.appgame.entity.TypeCustomerApp;
import com.sdys.appgame.server.BannerService;
import com.sdys.appgame.server.CustomerAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banner/contr")
public class BannerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BannerController.class);

    @Resource
    private BannerService bannerService;

    @Resource
    private CustomerAppService customerAppService;

    @PostMapping("/get_baner_list")
    public Object getBanerList(@RequestBody Map<String, String> map){
        LOGGER.info("getBanerList 获取到的参数为 = {}",map);
        Banner banner = new Banner();
        List<Map> bannerList = bannerService.getBanerList(banner);
        LOGGER.info("获取到的结果集为 = {}",bannerList);
        return bannerList;
    }


    @PostMapping("/del")
    public Object del(@RequestBody Map<String, String> map){
        Map<String, String> result = new HashMap<>();
        LOGGER.info("del 获取到的参数为 = {}",map);
        String bannerId = map.get("bannerId");
        String customerAppId = map.get("customerAppId");
        //删除轮播列表
        bannerService.deleteByPrimaryKey(Integer.valueOf(bannerId));
        //更新小程序是否添加轮播字段
        CustomerApp customerApp = new CustomerApp();
        customerApp.setId(Integer.valueOf(customerAppId));
        customerApp.setIsBaner(0);
        customerAppService.updateByPrimaryKeySelective(customerApp);
        result.put("code","200");
        result.put("message","轮播图删除成功！");
        return result;
    }



    @PostMapping("/update_index_item")
    public Object updateIndexIteml(@RequestBody Map<String, String> map){
        Map<String,Object> result = new HashMap<>();
        if(!StringUtils.isEmpty(map.get("banerList"))){
            JSONArray jsonArray = (JSONArray) JSONArray.parse(map.get("banerList").toString());
            for (Object o : jsonArray) {
                Map<String,Integer> typeObject = (Map<String, Integer>) o;
                Banner banner = new Banner();
                banner.setId(typeObject.get("bannerId"));
                banner.setIndexItem(typeObject.get("indexItem"));
                bannerService.updateByPrimaryKeySelective(banner);
            }
        }
        result.put("code","200");
        result.put("message","排序成功！");
        return result;
    }




}
