package com.sdys.appgame.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class CustomerApp implements Serializable {

    private Integer id;

    private String jumpName;

    private String jumpUrl;

    private String extraData;

    private Integer jumptype;

    private String introduce;

    private String logo;

    private Integer clickNub;

    private String bannerUrl;

    private Integer itemIndex;

    private String jumpAppId;

    /*@JSONField(serialize=false)*/
    private String typeId;

    private String typeValue;

    private String appId;

    private String secretKey;


    private String createUser;

    private Date createTime;

    private Integer isHide;


    private Integer isBaner;

    public Integer getIsBaner() {
        return isBaner;
    }

    public void setIsBaner(Integer isBaner) {
        this.isBaner = isBaner;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJumpName() {
        return jumpName;
    }

    public void setJumpName(String jumpName) {
        this.jumpName = jumpName;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public Integer getJumptype() {
        return jumptype;
    }

    public void setJumptype(Integer jumptype) {
        this.jumptype = jumptype;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getClickNub() {
        return clickNub;
    }

    public void setClickNub(Integer clickNub) {
        this.clickNub = clickNub;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Integer getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(Integer itemIndex) {
        this.itemIndex = itemIndex;
    }

    public String getJumpAppId() {
        return jumpAppId;
    }

    public void setJumpAppId(String jumpAppId) {
        this.jumpAppId = jumpAppId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "CustomerApp{" +
                "id=" + id +
                ", jumpName='" + jumpName + '\'' +
                ", jumpUrl='" + jumpUrl + '\'' +
                ", extraData='" + extraData + '\'' +
                ", jumptype=" + jumptype +
                ", introduce='" + introduce + '\'' +
                ", logo='" + logo + '\'' +
                ", clickNub=" + clickNub +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", itemIndex=" + itemIndex +
                ", jumpAppId='" + jumpAppId + '\'' +
                ", typeId=" + typeId +
                ", typeValue='" + typeValue + '\'' +
                ", appId='" + appId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", isHide=" + isHide +
                '}';
    }
}