package com.sdys.appgame.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class ConfigFileEntity {


    //注解，easypoi教程里有，时间转换、数据字典转换等
    @Excel(name = "appId", orderNum = "0")
    public String appId;
    @Excel(name = "key", orderNum = "1")
    public String key;
    @Excel(name = "name", orderNum = "2")
    public String name;
    @Excel(name = "desc", orderNum = "3")
    public String desc;
    @Excel(name = "isShow", orderNum = "4")
    public String isShow;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "ConfigFileEntity{" +
                "appId='" + appId + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", isShow='" + isShow + '\'' +
                '}';
    }
}
