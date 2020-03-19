package com.sdys.appgame.utils;

import com.sdys.appgame.entity.JsonResult;
import com.sdys.appgame.entity.ResultCode;

public class ResultUtils {

    public static JsonResult result(Integer status){
        if(status==1){
            return new JsonResult(ResultCode.SUCCESS);
        }else{
            return new JsonResult(ResultCode.FAIL);
        }
    }

}
