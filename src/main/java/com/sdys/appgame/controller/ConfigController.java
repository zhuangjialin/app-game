package com.sdys.appgame.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdys.appgame.entity.*;
import com.sdys.appgame.server.ConfigService;
import com.sdys.appgame.server.DailyVisitTrendService;
import com.sdys.appgame.utils.DateUtils;
import com.sdys.appgame.utils.FileUtil;
import com.sdys.appgame.utils.MapObjectTransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/config/contr")
public class ConfigController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);

    @Resource
    private DailyVisitTrendService dailyVisitTrendService;
    @Resource
    private ConfigService configService;


    @PostMapping("/getConfigList")
    public Object getConfigList(@RequestBody Map<String, String> map){
        LOGGER.info("getConfigList 获取到的参数为 = {}",map);
        Config config = new Config();
        String appId = map.get("appId");
        if(!StringUtils.isEmpty(appId)){
            config.setAppId(appId);
        }
        if(!StringUtils.isEmpty(map.get("isShow"))){
            config.setIsShow(Integer.valueOf(map.get("isShow")));
        }
        List<Config> configList = configService.getConfigList(config);
        LOGGER.info("获取到的结果集为 = {}",configList);
        return configList;
    }


    @PostMapping("/findConfigList")
    public Object findConfigList(@RequestBody Map<String, Object> map) throws Exception {
        LOGGER.info("findConfigList 获取到的参数为 = {}",map.toString());
        Map<String,Object> result = new HashMap<>();
        Integer pageNo = StringUtils.isEmpty(map.get("pageNo").toString()) ? 0:Integer.valueOf(map.get("pageNo").toString()) ;
        Integer pageSize = 10;
        Config config = (Config) MapObjectTransferUtil.mapToObject(map,Config.class);
        PageHelper.startPage(pageNo, pageSize);
        config.setRefDate(DateUtils.getDateBefore());
        List<Config> configList = configService.getConfigList(config);

        Map<String, String> allCountMap = new HashMap<>();
        allCountMap.put("refDate",DateUtils.getDateBefore());
        Map<String,String> allCount = dailyVisitTrendService.getAllCountData(allCountMap);
        PageInfo pageInfo = new PageInfo(configList);
        LOGGER.info("pageInfo 获取到的参数为 = {}",pageInfo.toString());
        result.put("total",pageInfo.getTotal());
        result.put("rows",configList);
        result.put("allCount",allCount);
        result.put("pageNo",pageInfo.getPageNum());
        LOGGER.info("获取到的结果集为 = {}",result);
        return result;
    }


    @PostMapping("/getConfigObject")
    public Object getConfigObject(@RequestBody Map<String, String> map){
        LOGGER.info("getConfigObject 获取到的参数为 = {}",map);
        Map<String,Object> result = new HashMap<>();
        Integer id = Integer.valueOf(map.get("id"));
        Config config = configService.selectByPrimaryKey(id);
        LOGGER.info("getConfigObject 获取到的参数为 config= {}",config.toString());
        return config;
    }




    @PostMapping("/addConfig")
    public JsonResult addConfig(@RequestBody Map<String, String> map){
        LOGGER.info("addConfig 获取到的参数为 = {}",map);
        String appId = map.get("appId");
        String param1 = map.get("param1");
        Integer isShow = StringUtils.isEmpty(map.get("isShow")) ? 0 : 1;

        Config config = new Config();
        if(!StringUtils.isEmpty(map.get("appId"))){
            config.setAppId(appId);
        }
        if(!StringUtils.isEmpty(map.get("param1"))){
            config.setParam1(param1);
        }
        if(!StringUtils.isEmpty(map.get("unitId"))){
            config.setParam4(map.get("unitId"));
        }
        config.setIsShow(isShow);
        int addObject = configService.insertSelective(config);
        if(addObject==1){
            return new JsonResult(ResultCode.SUCCESS);
        }else{
            return new JsonResult(ResultCode.FAIL);
        }
    }



    @PostMapping("/batAddConfig")
    public JsonResult batAddConfig(@RequestBody Map<String, String> map){
        LOGGER.info("batAddConfig 获取到的参数为 = {}",map);
        String appIds = map.get("appIds");
        Integer isShow = StringUtils.isEmpty(map.get("isShow")) ? 0 : 1;
        try {
            if(!StringUtils.isEmpty(appIds)){
                String[] appIdSArray = appIds.split("/");
                for (String s : appIdSArray) {
                    Config config = new Config();
                    config.setAppId(s);
                    List<Config> list = configService.getConfigList(config);
                    if(list.size()==0){
                        configService.insertSelective(config);
                    }
                }
            }else{
                return new JsonResult(ResultCode.FAIL,"appid输入有误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.FAIL,"系统报错");
        }
            return new JsonResult(ResultCode.SUCCESS);
    }



    @PostMapping("/editConfig")
    public JsonResult editConfig(@RequestBody Map<String, Object> map){
        LOGGER.info("editConfig 获取到的参数为 = {}",map);
        try {
            Config config = (Config) MapObjectTransferUtil.mapToObject(map, Config.class);

            int editObject = 0;
            if(StringUtils.isEmpty(config.getId())){
                editObject = configService.insertSelective(config);
                return new JsonResult(ResultCode.SUCCESS,"添加成功");
            }else{
                editObject = configService.updateByPrimaryKeySelective(config);
                return new JsonResult(ResultCode.SUCCESS,"修改成功");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.FAIL,"系统报错");
        }
    }


    @PostMapping("/delConfig")
    public JsonResult delConfig(@RequestBody Map<String, String> map){
        LOGGER.info("delConfig 获取到的参数为 = {}",map);
        Integer id = Integer.valueOf(map.get("id"));
        int editObject = configService.deleteByPrimaryKey(id);
        if(editObject==1){
            return new JsonResult(ResultCode.SUCCESS);
        }else{
            return new JsonResult(ResultCode.FAIL);
        }
    }


    //Excel导入
    @RequestMapping("importExcel")
    public JsonResult importExcel(@RequestParam("textFile") MultipartFile file, HttpServletRequest request) throws Exception {
        //String filePath = "F:\\故乡南.xls";
        System.out.println(file);//用来检查前端是否把文件传过来
        //解析excel，
        try {
            List<ConfigFileEntity> personList = FileUtil.importExcel(file, 1, 1, ConfigFileEntity.class);
            System.out.println("导入数据一共【" + personList.size() + "】行");
            LOGGER.info(personList.toString());
            for (ConfigFileEntity configFileEntity : personList) {
                LOGGER.info(configFileEntity.toString());
                Config config = new Config();
                config.setAppId(configFileEntity.getAppId());
                List<Config> list = configService.getConfigList(config);
                if(list.size()==0){
                    config.setParam1(configFileEntity.getName());
                    config.setParam2(configFileEntity.desc);
                    config.setParam3(configFileEntity.getKey());
                    configService.insertSelective(config);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.FAIL,e.getMessage());
        }
        return new JsonResult(ResultCode.SUCCESS);
    }





    @PostMapping("/batch_update_is_show")
    public void batchUpdateIsShow(@RequestBody Map<String, String> map){
        LOGGER.info("batchUpdateIsShow 获取到的参数为 = {}",map);
        Integer isShow = Integer.valueOf(map.get("isShow"));
        configService.batchUpdate(isShow);
    }


    @GetMapping("/batch_update_show/{isShow}")
    public void batchUpdateShow(@PathVariable Integer isShow){
        LOGGER.info("batchUpdateIsShow 获取到的参数为 = {}",isShow);
        configService.batchUpdate(isShow);
    }




    @PostMapping("/update_is_batch")
    public void updateIsBatch(@RequestBody Map<String, String> map){
        LOGGER.info("updateIsBatch 获取到的参数为 = {}",map);
        Integer isBatch = Integer.valueOf(map.get("isBatch"));
        Integer id = Integer.valueOf(map.get("id"));
        Config config = new Config();
        config.setId(id);
        config.setIsBatch(isBatch);
        configService.updateByPrimaryKeySelective(config);
    }




}
