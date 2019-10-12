package com.liyan.store.domain.result;

import java.math.BigDecimal;

public class OrdersProductResult {
    private Long ordersProductId;
    private Integer number;
    private BigDecimal price;
    private Long diyAttrId;
    private String diyAttrName;
    private String mainImg;
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public Long getOrdersProductId() {
        return ordersProductId;
    }

    public void setOrdersProductId(Long ordersProductId) {
        this.ordersProductId = ordersProductId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getDiyAttrId() {
        return diyAttrId;
    }

    public void setDiyAttrId(Long diyAttrId) {
        this.diyAttrId = diyAttrId;
    }

    public String getDiyAttrName() {
        return diyAttrName;
    }

    public void setDiyAttrName(String diyAttrName) {
        this.diyAttrName = diyAttrName;
    }
}
