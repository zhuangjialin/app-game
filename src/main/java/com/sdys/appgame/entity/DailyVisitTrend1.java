package com.sdys.appgame.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DailyVisitTrend1 {
    private Integer id;

    private String ghid;

    private String appid;

    private String refDate;

    private Integer sessionCnt;

    private Integer visitPv;

    private Integer visitUv;

    private Integer visitUvNew;

    private BigDecimal stayTimeUv;

    private BigDecimal stayTimeSession;

    private BigDecimal visitDepth;

    private Integer configId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGhid() {
        return ghid;
    }

    public void setGhid(String ghid) {
        this.ghid = ghid == null ? null : ghid.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getRefDate() {
        return refDate;
    }

    public void setRefDate(String refDate) {
        this.refDate = refDate == null ? null : refDate.trim();
    }

    public Integer getSessionCnt() {
        return sessionCnt;
    }

    public void setSessionCnt(Integer sessionCnt) {
        this.sessionCnt = sessionCnt;
    }

    public Integer getVisitPv() {
        return visitPv;
    }

    public void setVisitPv(Integer visitPv) {
        this.visitPv = visitPv;
    }

    public Integer getVisitUv() {
        return visitUv;
    }

    public void setVisitUv(Integer visitUv) {
        this.visitUv = visitUv;
    }

    public Integer getVisitUvNew() {
        return visitUvNew;
    }

    public void setVisitUvNew(Integer visitUvNew) {
        this.visitUvNew = visitUvNew;
    }

    public BigDecimal getStayTimeUv() {
        return stayTimeUv;
    }

    public void setStayTimeUv(BigDecimal stayTimeUv) {
        this.stayTimeUv = stayTimeUv;
    }

    public BigDecimal getStayTimeSession() {
        return stayTimeSession;
    }

    public void setStayTimeSession(BigDecimal stayTimeSession) {
        this.stayTimeSession = stayTimeSession;
    }

    public BigDecimal getVisitDepth() {
        return visitDepth;
    }

    public void setVisitDepth(BigDecimal visitDepth) {
        this.visitDepth = visitDepth;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}