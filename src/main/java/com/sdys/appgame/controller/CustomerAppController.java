package com.sdys.appgame.controller;


import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiniu.api.auth.AuthException;
import com.sdys.appgame.entity.*;
import com.sdys.appgame.server.*;
import com.sdys.appgame.utils.DateUtils;
import com.sdys.appgame.utils.MD5Util;
import com.sdys.appgame.utils.MapObjectTransferUtil;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/customerApp/contr")
public class CustomerAppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAppController.class);


    @Resource
    private CustomerAppService customerAppService;


    @Resource
    private ConfigService configService;

    @Resource
    private TypeCustomerAppService typeCustomerAppService;

    @Resource
    private TypeService typeService;

    @Resource
    private BannerService bannerService;


    @Value("${imageUrl}")
    private String imageUrl;



    @Value("${banerUrl}")
    private String banerUrl;

    @GetMapping("/getCustomerAppList")
    public JsonResult getCustomerAppList(@RequestBody Map<String,String> map){
        LOGGER.info("getCustomerAppList 获取的参数为 ：{}",map);
        CustomerApp customerApp = new CustomerApp();
        String appId = map.get("appId");
        String jumpName = map.get("jumpName");

        if(!StringUtils.isEmpty(appId)){
            customerApp.setJumpAppId(appId);
        }
        if(!StringUtils.isEmpty(jumpName)){
            customerApp.setJumpName(jumpName);
        }
        List<CustomerApp> customerAppList = customerAppService.getCustomerAppList(customerApp);
        return new JsonResult(ResultCode.SUCCESS,customerAppList);
    }



    @GetMapping("/cms/findCustomerAppList")
    public JsonResult findCustomerAppList(@RequestBody Map<String,String> map){
        LOGGER.info("findCustomerAppList 获取的参数为 ：{}",map);
        Map<String,Object> result = new HashMap<>();
        CustomerApp customerApp = new CustomerApp();
        Integer pageSize = 10;
        Integer pageNo = StringUtils.isEmpty(map.get("pageNo")) ? 0:Integer.valueOf(map.get("pageNo")) ;
        PageHelper.startPage(pageNo, pageSize);
        List<CustomerApp> customerAppList = customerAppService.getCustomerAppList(customerApp);
        PageInfo pageInfo = new PageInfo(customerAppList);
        result.put("total",pageInfo.getTotal());
        result.put("rows",customerAppList);
        result.put("pageNo",pageInfo.getPageNum());
        return new JsonResult(ResultCode.SUCCESS,customerAppList);
    }


    @PostMapping("/addCustomerApp")
    public Object addCustomerApp(@RequestBody Map<String,Object> paramMap) throws Exception {
        LOGGER.info("addCustomerApp 获取的参数为 ：{}",paramMap.toString());

        Map<String,Object> result = new HashMap<>();
        JSONArray typeJsonArray = (JSONArray) paramMap.get("typeId");
        String typeIds = "";
        for (Object o : typeJsonArray) {
            typeIds += String.valueOf(o)+",";
        }
        paramMap.put("typeId",typeIds);
        CustomerApp customerApp = (CustomerApp) MapObjectTransferUtil.mapToObject(paramMap,CustomerApp.class);
        try {
            customerApp.setJumptype(0);
            int id = customerAppService.insertSelective(customerApp);
            //添加type
            String[] typeIdArray = String.valueOf(paramMap.get("typeId")).split(",");
            TypeCustomerApp typeCustomerApp = new TypeCustomerApp();
            typeCustomerApp.setCustomerAppId(customerApp.getId());
            for (String typeId : typeIdArray) {
                typeCustomerApp.setTypeId(Integer.valueOf(typeId));
                typeCustomerAppService.insertSelective(typeCustomerApp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("code",ResultCode.SUCCESS);
        result.put("message","添加成功");
        result.put("id",customerApp.getId());
        return result;
    }

    @PostMapping("/findByPrimaryKey")
    public Object findByPrimaryKey(@RequestBody Map<String, Object> map){
        LOGGER.info("findByPrimaryKey 获取到的参数为 = {}",map.toString());
        Map<String,Object> result = new HashMap<>();
        try {
            CustomerApp customerApp = (CustomerApp) MapObjectTransferUtil.mapToObject(map, CustomerApp.class);
            Integer pageNo = StringUtils.isEmpty(map.get("pageNo")) ? 0:Integer.valueOf(String.valueOf(map.get("pageNo"))) ;
            Integer pageSize = 10;
            PageHelper.startPage(pageNo, pageSize);
            List<CustomerApp> configList = customerAppService.findByPrimaryKey(customerApp);
            PageInfo pageInfo = new PageInfo(configList);
            result.put("total",pageInfo.getTotal());
            result.put("rows",configList);
            result.put("pageNo",pageInfo.getPageNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }





    @PostMapping("/edit_customer_app")
    public Object editCustomerApp(@RequestBody Map<String, Object> map) throws Exception {
        LOGGER.info("changeIsHide 获取到的参数为 = {}",map.toString());
        JSONArray typeIdArrys = (JSONArray) map.get("typeId");
        LOGGER.info("changeIsHide 获取到的参数为 = {}",typeIdArrys.toString());
        String typeIds = "";
        if(!StringUtils.isEmpty(map.get("typeId"))) {
            for (Object o : typeIdArrys) {
                typeIds += String.valueOf(o) + ",";
            }
            map.put("typeId", typeIds.substring(0, typeIds.length() - 1));
        }

        CustomerApp customerApp = (CustomerApp) MapObjectTransferUtil.mapToObject(map, CustomerApp.class);
        int customerAppId = customerApp.getId();
        String[] typeIdArray = customerApp.getTypeId().split(",");
        List<String> typeList = Arrays.asList(typeIdArray);
        customerAppService.updateByPrimaryKeySelective(customerApp);


        Map<Integer,Integer> typeMap = new HashMap<>();
        TypeCustomerApp typeCustomerApp = new TypeCustomerApp();
        typeCustomerApp.setCustomerAppId(customerAppId);
        List<TypeCustomerApp> list = typeCustomerAppService.selectListByPrimary(typeCustomerApp);
        typeCustomerAppService.deleteByParam(typeCustomerApp);
        int maxIndexItem = 0;
        if(list.size()>0){
            for (TypeCustomerApp app : list) {
                typeMap.put(app.getTypeId(),app.getIndexItem());
                if(!StringUtils.isEmpty(app.getIndexItem())){
                    if(app.getIndexItem() > maxIndexItem ){
                        maxIndexItem = app.getIndexItem();
                    }
                }
            }
        }

        if(!StringUtils.isEmpty(map.get("typeId"))){
            for (String o : typeIdArray) {
                typeCustomerApp.setTypeId(Integer.valueOf(o));
                if(StringUtils.isEmpty(typeMap.get(Integer.valueOf(o)))){
                    maxIndexItem = maxIndexItem+1;
                    typeCustomerApp.setIndexItem(maxIndexItem);
                }else{
                    typeCustomerApp.setIndexItem(typeMap.get(Integer.valueOf(o)));
                }
                typeCustomerAppService.insertSelective(typeCustomerApp);
            }
        }
        return new JsonResult(ResultCode.SUCCESS)    ;
    }


    @PostMapping("/changeIsHide")
    public Object changeIsHide(@RequestBody Map<String, Object> map){
        LOGGER.info("changeIsHide 获取到的参数为 = {}",map.toString());
        JSONArray typeJsonArray = new JSONArray();
        if(!StringUtils.isEmpty(map.get("typeId"))){
            typeJsonArray = (JSONArray) map.get("typeId");
            String typeIds = "";
            for (Object o : typeJsonArray) {
                typeIds += String.valueOf(o)+",";
            }
            map.put("typeId",typeIds.substring(0,typeIds.length() -1));
        }
        try {
            //更新小程序列表
            CustomerApp customerApp = (CustomerApp) MapObjectTransferUtil.mapToObject(map, CustomerApp.class);
            int update = customerAppService.updateByPrimaryKeySelective(customerApp);
            if(!StringUtils.isEmpty(map.get("typeId"))){
                //删除关联表
                TypeCustomerApp typeCustomerApp = new TypeCustomerApp();
                typeCustomerApp.setCustomerAppId(customerApp.getId());
                typeCustomerAppService.deleteByParam(typeCustomerApp);
                //重新插入关联表
                for (Object o : typeJsonArray) {
                    typeCustomerApp.setTypeId(Integer.valueOf(String.valueOf(o)));
                } typeCustomerAppService.insertSelective(typeCustomerApp);
            }
            if(update==1){
                return new JsonResult(ResultCode.SUCCESS);
            }else{
                return new JsonResult(ResultCode.FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





    @PostMapping("/getBoxList")
    public Object getBoxList(@RequestBody Map<String, String> map){
        LOGGER.info("getBoxList 获取到的参数为 = {}",map.toString());
        Map<String,Object> result = new HashMap<>();
        com.sdys.appgame.entity.Config config = new com.sdys.appgame.entity.Config();
        config.setIsBox(1);
        List<com.sdys.appgame.entity.Config> boxList = configService.findConfigList(config);
        Type type = new Type();
        List<Type> typeList = typeService.selectByParam(type);
        result.put("boxList",boxList);
        result.put("typeList",typeList);
        return result;
    }

    @PostMapping("/delCustomerApp")
    public JsonResult delCustomerApp(@RequestBody Map<String, String> map){
        Integer id = Integer.valueOf(map.get("id"));
        LOGGER.info("delCustomerApp 获取到的参数为 id= {}",id);
        //删除绑定的分类
        TypeCustomerApp typeCustomerApp = new TypeCustomerApp();
        typeCustomerApp.setCustomerAppId(id);
        typeCustomerAppService.deleteByParam(typeCustomerApp);
        //删除小程序的列表的
        int editObject = customerAppService.deleteByPrimaryKey(id);
        if(editObject==1){
            return new JsonResult(ResultCode.SUCCESS);
        }else{
            return new JsonResult(ResultCode.FAIL);
        }
    }



    @PostMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(@RequestBody Map<String, String> map){
        LOGGER.info("selectByPrimaryKey 获取到的参数为 = {}",map);
        Integer id = Integer.valueOf(map.get("id"));
        CustomerApp customerApp = customerAppService.selectByPrimaryKey(id);
        return customerApp;
    }

    @PostMapping("/uploadImg")
    public Object uploadImg(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws AuthException, JSONException {
        LOGGER.info("selectByPrimaryKey 获取到的参数为 = {}",file);
        Map<String,Object> result = new HashMap<>();
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = MD5Util.md5Encrypt32Upper(fileName)+suffixName;
        //指定本地文件夹存储图片
        String filePath = "C:/images/";
        try {
            //将图片保存到static文件夹里
            file.transferTo(new File(filePath+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String key = file.getOriginalFilename();
        Config.ACCESS_KEY = "xdRibPUAFfzrNbrezZ7Pk5sz9FqamBcgP2ZTwlO1";
        Config.SECRET_KEY = "aUuAvgKX38s0-Mv8ECCE6Id8T-cRLiV2r8Je2g5r";
        Config.UP_HOST = "http://up-z2.qiniup.com";
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        // 请确保该bucket已经存在
        String bucketName = "app-game";
        PutPolicy putPolicy = new PutPolicy(bucketName);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
        PutRet ret = IoApi.putFile(uptoken, key,filePath+fileName, extra);
        LOGGER.info("selectByPrimaryKey 获取到的参数为 = {}",ret.toString());
        result.put("url","http://image.eecup.cn/"+key);
        result.put("name",key);
        return result;
    }


    @PostMapping("/addLogoImages")
    public JsonResult addLogoImages(@RequestBody Map<String, String> map){
        LOGGER.info("delCustomerApp 获取到的参数为 = {}",map.toString());
        String logo = map.get("logo");
        String bannerUrl = map.get("bannerUrl");
        Integer id = Integer.valueOf(map.get("id"));
        CustomerApp customerApp = new CustomerApp();
        customerApp.setLogo(logo);
        customerApp.setId(id);
        customerApp.setBannerUrl(bannerUrl);
        int add = customerAppService.updateByPrimaryKeySelective(customerApp);
        if(add==1){
            return new JsonResult(ResultCode.SUCCESS);
        }else{
            return new JsonResult(ResultCode.FAIL);
        }
    }


    @PostMapping("/edit_is_baner")
    public Object editIsBaner(@RequestBody Map<String, Object> map) throws Exception {
        LOGGER.info("editIsBaner 获取到的参数为 = {}",map.toString());
        CustomerApp customerApp = (CustomerApp) MapObjectTransferUtil.mapToObject(map, CustomerApp.class);
        customerAppService.updateByPrimaryKeySelective(customerApp);

        Banner banner = new Banner();
        banner.setCustomerAppId(customerApp.getId());
        if(customerApp.getIsBaner() ==1){
            bannerService.insertSelective(banner);
        }else{
            bannerService.deleteByParms(banner);
        }
        return new JsonResult(ResultCode.SUCCESS)    ;
    }







    @PostMapping("/advertising_statistics")
    public Object advertisingStatistics(@RequestBody Map<String, Object> map) throws Exception {
        Map<String, Object> result = new HashMap<>();
        LOGGER.info("advertisingStatistics 获取到的参数为 = {}",map.toString());
        String createTime = String.valueOf(map.get("createTime"));
        if(StringUtils.isEmpty(map.get("createTime"))){
            createTime =DateUtils.getDateByString();
            map.put("createTime",createTime);
        }
        LOGGER.info("advertisingStatistics 获取到的参数为 = {}",map.toString());
        List<Map> list = customerAppService.advertisingStatistics(map);
        result.put("createTime",createTime);
        result.put("list",list);
        return result   ;
    }



}
