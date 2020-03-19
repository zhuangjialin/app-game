package com.sdys.appgame.entity;


public class Config {

    public Config(String appId) {
        this.appId = appId;
    }


    public Config() {
    }


    private String refDate;

    private String visitPv;

    private String visitUvNew;

    private String allVisitUvNew;

    private String pv;

    private String uv;


    /* @JSONField(serialize=false)*/
    private Integer id;


    private Integer isBatch;



    private String unitId;

    private Integer isShow;

    /*@JSONField(name="name")*/
    private String param1;

   /* @JSONField(name="desc")*/
    private String param2;

 /*   @JSONField(name="key")*/
    private String param3;

   /* @JSONField(name="isBox")*/
    private String param4;

    private String appId;

    private String title;


    private String description;

    private Integer isBox;


    public String getRefDate() {
        return refDate;
    }

    public void setRefDate(String refDate) {
        this.refDate = refDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsBox() {
        return isBox;
    }

    public void setIsBox(Integer isBox) {
        this.isBox = isBox;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1 == null ? null : param1.trim();
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2 == null ? null : param2.trim();
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3 == null ? null : param3.trim();
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4 == null ? null : param4.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }


    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getVisitPv() {
        return visitPv;
    }

    public void setVisitPv(String visitPv) {
        this.visitPv = visitPv;
    }

    public String getVisitUvNew() {
        return visitUvNew;
    }

    public void setVisitUvNew(String visitUvNew) {
        this.visitUvNew = visitUvNew;
    }

    public String getAllVisitUvNew() {
        return allVisitUvNew;
    }

    public void setAllVisitUvNew(String allVisitUvNew) {
        this.allVisitUvNew = allVisitUvNew;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public Integer getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(Integer isBatch) {
        this.isBatch = isBatch;
    }
}