package com.liyan.store.domain.result;

import com.liyan.store.domain.OrdersProduct;

import java.util.ArrayList;
import java.util.List;

public class OrdersResult {
    private Long ordersId;
    private String number;//单号
    private Integer status;//记录状态0:待付款 1：未发货 2：已发货 3：退款中 4：交易完成 5：交易关闭
    private List<OrdersProductResult> ordersProductList;

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrdersProductResult> getOrdersProductList() {
        if (ordersProductList==null){
            ordersProductList=new ArrayList<>();
        }
        return ordersProductList;
    }

    public void setOrdersProductList(List<OrdersProductResult> ordersProductList) {
        this.ordersProductList = ordersProductList;
    }
}
