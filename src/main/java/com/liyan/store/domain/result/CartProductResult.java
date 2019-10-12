package com.liyan.store.domain.result;

import com.liyan.store.domain.ProductAttr;

import java.math.BigDecimal;

public class CartProductResult {
    private Long cartProductId;
    private Integer number;
    private Long shopId;
    private String shopName;
    private String productName;
    private Long diyAttrId;
    private String diyAttrName;
    private BigDecimal price;
    private String mainImg;

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

    public Long getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(Long cartProductId) {
        this.cartProductId = cartProductId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
