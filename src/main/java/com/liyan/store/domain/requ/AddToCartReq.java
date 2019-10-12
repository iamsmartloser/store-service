package com.liyan.store.domain.requ;

import javax.persistence.criteria.CriteriaBuilder;

public class AddToCartReq {

    private Long userId;
    private Long diyAttrId;
    private Integer number;
    private Integer page;
    private Integer size;
    private Long cartProductId;

    public Long getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(Long cartProductId) {
        this.cartProductId = cartProductId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getDiyAttrId() {
        return diyAttrId;
    }

    public void setDiyAttrId(Long diyAttrId) {
        this.diyAttrId = diyAttrId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
