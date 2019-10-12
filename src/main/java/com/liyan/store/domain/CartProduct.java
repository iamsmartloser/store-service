package com.liyan.store.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CartProduct {

    @Id
    @GeneratedValue
    private Long cartProductId;

    private Integer number;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch= FetchType.LAZY,targetEntity=ProductAttr.class)
    @JoinColumn(name="diyAttrId")
    @JsonIgnore
    private ProductAttr productAttr;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,targetEntity = ShopingCar.class)
    private List<ShopingCar> carList;

    //店铺ID
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY, targetEntity = Shop.class)
    @JoinColumn(name = "shopId")
    private Shop shop;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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

    public ProductAttr getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(ProductAttr productAttr) {
        this.productAttr = productAttr;
    }

    public List<ShopingCar> getCarList() {
        if (carList==null)
            carList=new ArrayList<>();
        return carList;
    }

    public void setCarList(List<ShopingCar> carList) {
        this.carList = carList;
    }
}
