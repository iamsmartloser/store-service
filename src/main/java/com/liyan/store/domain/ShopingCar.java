package com.liyan.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShopingCar {
    @Id
    @GeneratedValue
    private Long carId;
    private Integer productNum;
    private Integer status;


    //关联商品ID多对多
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,targetEntity = CartProduct.class)
//    @JoinTable(name = "cars_products", joinColumns = {
//            @JoinColumn(name = "car_id")}, inverseJoinColumns =
//            {@JoinColumn(name = "cart_product_id")})
    private List<CartProduct> productList;

    //关联用户ID
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.LAZY,targetEntity=User.class)
    @JoinColumn(name="userId")
    private User user;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CartProduct> getProductList() {
        if (productList==null){
            productList=new ArrayList<>();
        }
        return productList;
    }

    public void setProductList(List<CartProduct> productList) {
        this.productList = productList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
