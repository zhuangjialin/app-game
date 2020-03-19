package com.sdys.appgame.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Type {
    /*@JSONField(serialize=false)*/
    private Integer id;

    private String typeValue;

    private String typeLogo;

    private Integer showType;

    private Integer isHide;

    private Integer serialNumber;


    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    private List<CustomerApp> lists;

    public List<CustomerApp> getLists() {
        return lists;
    }

    public void setLists(List<CustomerApp> lists) {
        this.lists = lists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue == null ? null : typeValue.trim();
    }

    public String getTypeLogo() {
        return typeLogo;
    }

    public void setTypeLogo(String typeLogo) {
        this.typeLogo = typeLogo == null ? null : typeLogo.trim();
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }



}