package com.sdys.appgame.controller;

import com.sdys.appgame.entity.Config;
import com.sdys.appgame.server.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common/contr")
public class CommonUtilController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtilController.class);


    @Resource
    private ConfigService configService;


    @PostMapping("/findBoxList")
    private Object findBoxList(@RequestBody Map<String,String> map){
        List<Map<String,String>> resultList = new ArrayList<>();
        LOGGER.info("findBoxList 获取的参数为 ：{}",map.toString());
        String isBox = map.get("isBox");
        Config config = new Config();
        config.setParam4(isBox);
        List<Config> list = configService.findConfigList(config);
        for (Config config1 : list) {
            Map<String,String> result = new HashMap<>();
            result.put("label",config1.getParam1());
            result.put("value",config1.getId()+"");
            resultList.add(result);
        }
        return resultList;
    }

   /* private Map<String,String> ListToSelectObject(List<?> list){
        Map<String,String> result = new HashMap<>();



    }
*/
}
