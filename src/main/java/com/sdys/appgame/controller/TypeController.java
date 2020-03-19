package com.sdys.appgame.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdys.appgame.entity.*;
import com.sdys.appgame.server.CustomerAppService;
import com.sdys.appgame.server.TypeCustomerAppService;
import com.sdys.appgame.server.TypeService;
import com.sdys.appgame.utils.MapObjectTransferUtil;
import com.sdys.appgame.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type/contr")
public class TypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeController.class);

    @Resource
    private TypeService typeService;


    @Resource
    private CustomerAppService customerAppService;

    @Resource
    private TypeCustomerAppService typeCustomerAppService;


    @PostMapping("/getTypeList")
    public JsonResult getTypeList(){
        List<Type> typeList  = typeService.selectByParam(new Type());
        return new JsonResult(ResultCode.SUCCESS,typeList);
    }


    @GetMapping("/getType")
    public JsonResult getType(@PathVariable Integer id){
        Type type  = typeService.selectByPrimaryKey(id);
        return new JsonResult(ResultCode.SUCCESS,type);
    }



    @PostMapping("/find_type")
    public Object findType(@RequestBody Map<String, String> map){
        Map<String,Object> result = new HashMap<>();
        Integer id = Integer.valueOf(map.get("id"));
        Type type  = typeService.selectByPrimaryKey(id);
        result.put("code","200");
        result.put("data",type);
        return type;
    }


    @PostMapping("/add_type")
    public JsonResult addType(@RequestBody Map<String, Object> map) throws Exception {
        LOGGER.info("addType 获取到的参数为 ：{}",map);
        Type type = (Type) MapObjectTransferUtil.mapToObject(map, Type.class);
        int addObject = typeService.insertSelective(type);
        return ResultUtils.result(addObject);
    }



    @PostMapping("/editType")
    public JsonResult editType(@RequestBody Map<String, Object> map) throws Exception {
        LOGGER.info("editType 获取到的参数为 ：{}",map);
        Type type = (Type) MapObjectTransferUtil.mapToObject(map,Type.class);
        int editObject = typeService.updateByPrimaryKeySelective(type);
        return ResultUtils.result(editObject);
    }



    @PostMapping("/delType")
    public JsonResult delType(@RequestBody Map<String, String> map){
        LOGGER.info("delType 获取到的参数为 ：{}",map);
        int id = Integer.valueOf(map.get("id"));
        int count = typeCustomerAppService.selectCountByParam(new TypeCustomerApp(id));
        if(count>0){
            return new JsonResult(ResultCode.RELATED_DATA);
        }
        int delObject = typeService.deleteByPrimaryKey(id);
        return ResultUtils.result(delObject);
    }



    @PostMapping("/find_list")
    public Object findList(){
        Map<String,Object> result = new HashMap<>();
        PageHelper.startPage(0, 10);
        List<Type> typeList = typeService.selectByParam(new Type());
        PageInfo pageInfo = new PageInfo(typeList);
        LOGGER.info("pageInfo 获取到的参数为 = {}",pageInfo.toString());
        result.put("total",pageInfo.getTotal());
        result.put("rows",typeList);
        result.put("pageNo",pageInfo.getPageNum());
        LOGGER.info("findList 获取到的结果集为 = {}",result);
        return result;
    }


    @PostMapping("/type_data_dictionary")
    public Object TypeDataDictionary(){
        List<Type> typeList  = typeService.selectByParam(new Type());
        return typeList;
    }

    @PostMapping("/find_types")
    public Object findTypes(@RequestBody Map<String, String> map){
        Map<String,Object> result = new HashMap<>();
        List<Map> typeList  = typeService.findTypesList(map);
        return typeList;
    }



    @PostMapping("/update_index_item")
    public Object updateIndexItem(@RequestBody Map<String, Object> map){
        Map<String,Object> result = new HashMap<>();
            if(!StringUtils.isEmpty(map.get("typeList"))){
                JSONArray jsonArray = (JSONArray) JSONArray.parse(map.get("typeList").toString());
                for (Object o : jsonArray) {
                    Map<String,Integer> typeObject = (Map<String, Integer>) o;
                    TypeCustomerApp typeCustomerApp = new TypeCustomerApp();
                    typeCustomerApp.setId(typeObject.get("typeCustomerAppId"));
                    typeCustomerApp.setIndexItem(typeObject.get("indexItem"));
                    typeCustomerAppService.updateByPrimaryKeySelective(typeCustomerApp);
                }
            }
        result.put("code","200");
        result.put("message","排序成功！");
        return result;
    }



    @PostMapping("/del")
    public Object del(@RequestBody Map<String, String> map){
        Map<String,Object> result = new HashMap<>();
        String typeCustomerAppId = map.get("typeCustomerAppId");
        String customerAppId = map.get("customerAppId");
        String typeId = map.get("typeId");
        String customerAppTypdId = map.get("customerAppTypdId");


        String newTypeId = "";
        String[] customerAppTypdIdArray = customerAppTypdId.split(",");
        for (String s : customerAppTypdIdArray) {
            if(!s.equals(typeId)){
                newTypeId += s+",";
            }
        }
        if(newTypeId.contains(",")){
            newTypeId = newTypeId.substring(0,newTypeId.length()-1);
        }
        LOGGER.info("findList 获取到的结果集为 = {}",newTypeId);
        //删除关联分类
        typeCustomerAppService.deleteByPrimaryKey(Integer.valueOf(typeCustomerAppId));

        CustomerApp customerApp = new CustomerApp();
        customerApp.setId(Integer.valueOf(customerAppId));
        customerApp.setTypeId(newTypeId);
        customerAppService.updateByPrimaryKeySelective(customerApp);
        result.put("code","200");
        result.put("message","删除成功！");
        return result;
    }




    @PostMapping("/delete_type")
    public Object deleteType(@RequestBody Map<String, String> map){
        Map<String,Object> result = new HashMap<>();
        int typeId = Integer.valueOf(map.get("id"));
        typeService.deleteType(typeId);
        typeService.deleteByPrimaryKey(typeId);
        result.put("code","200");
        return result;
    }



}
