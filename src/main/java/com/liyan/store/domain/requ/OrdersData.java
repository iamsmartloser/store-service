package com.liyan.store.domain.requ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyan on 2018/5/8.
 */

public class OrdersData {
    private Long shopId;
    private List<OrderItem> orderItemList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<OrderItem> getOrderItemList() {
        if (orderItemList==null)
            orderItemList=new ArrayList<>();
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
