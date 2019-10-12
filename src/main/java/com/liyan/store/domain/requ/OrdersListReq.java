package com.liyan.store.domain.requ;

public class OrdersListReq {
    private Long userId;
    private Integer page;
    private Integer size;
    private Integer status;//0:未付款 1：未发货 2：已发货 3：退款中 4：交易完成 5：交易关闭,-1:所有订单

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
