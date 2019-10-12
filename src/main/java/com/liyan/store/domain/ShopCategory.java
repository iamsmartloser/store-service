package com.liyan.store.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class ShopCategory {

    @Id
    @GeneratedValue
    private Integer shopCategoryId;
    private String name;
    private Integer weight;
    private String introduce;
    private String img;
    private Timestamp createTime;
    private Timestamp updateTime;

    @OneToMany(fetch= FetchType.LAZY,targetEntity=Shop.class)
    @JoinColumn(name = "shopId")
    private List<Shop> shopList;

    public Integer getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Integer shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescribe() {
        return introduce;
    }

    public void setDescribe(String describe) {
        this.introduce = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
