package com.sdys.appgame.entity;


import org.springframework.beans.factory.annotation.Value;

public class XcxShare {


    @Value("${xcxShare.shareTitle}")
    private String shareTitle;

    @Value("${xcxShare.shareImg}")
    private String shareImg;


    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }
}
