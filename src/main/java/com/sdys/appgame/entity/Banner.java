package com.sdys.appgame.entity;

public class Banner {
    private Integer id;

    private Integer customerAppId;

    private Integer indexItem;


    public Integer getIndexItem() {
        return indexItem;
    }

    public void setIndexItem(Integer indexItem) {
        this.indexItem = indexItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerAppId() {
        return customerAppId;
    }

    public void setCustomerAppId(Integer customerAppId) {
        this.customerAppId = customerAppId;
    }
}