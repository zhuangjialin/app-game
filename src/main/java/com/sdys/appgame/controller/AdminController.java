package com.sdys.appgame.controller;


import com.sdys.appgame.entity.JsonResult;
import com.sdys.appgame.entity.ResultCode;
import com.sdys.appgame.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/admin")
public class AdminController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);



    @PostMapping(value="/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult login(@RequestBody User user){
        LOGGER.info("************login**************" +user.toString());
        return new JsonResult(ResultCode.SUCCESS);

      /*  String username = map.get("username");
        String password = map.get("password");

        if("admin".equals(username)&&"admin123".equals(password)){
            return new JsonResult(ResultCode.SUCCESS);
        }else{
            return new JsonResult(ResultCode.FAIL,"");


        }*/





    }

}
