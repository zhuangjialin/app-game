package com.sdys.appgame.entity;

import java.util.Date;

public class UvPvRecord {

    private Integer id;

    private String appId;

    private Integer cutomerId;

    private Integer uv;

    private Integer pv;

    private String createTime;

    private Date updateTime;

    private Date beginTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public Integer getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(Integer cutomerId) {
        this.cutomerId = cutomerId;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }


    @Override
    public String toString() {
        return "UvPvRecord{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", cutomerId=" + cutomerId +
                ", uv=" + uv +
                ", pv=" + pv +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                ", beginTime=" + beginTime +
                '}';
    }
}