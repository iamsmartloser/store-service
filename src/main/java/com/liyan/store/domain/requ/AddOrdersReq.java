package com.liyan.store.domain.requ;

import java.util.ArrayList;
import java.util.List;

public class AddOrdersReq {
    //以下时收货信息，暂不维护地址表，因为本项目暂时没用到，也不用单独维护收货地址表
    private Long userId;
    private String personName;
    private String personTel;
    private String personAddr;
    private String postCodes;
    private List<OrdersData> ordersDataList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonTel() {
        return personTel;
    }

    public void setPersonTel(String personTel) {
        this.personTel = personTel;
    }

    public String getPersonAddr() {
        return personAddr;
    }

    public void setPersonAddr(String personAddr) {
        this.personAddr = personAddr;
    }

    public String getPostCodes() {
        return postCodes;
    }

    public void setPostCodes(String postCodes) {
        this.postCodes = postCodes;
    }

    public List<OrdersData> getOrdersDataList() {
        if (ordersDataList==null)
            ordersDataList=new ArrayList<>();
        return ordersDataList;
    }

    public void setOrdersDataList(List<OrdersData> ordersDataList) {
        this.ordersDataList = ordersDataList;
    }
}
