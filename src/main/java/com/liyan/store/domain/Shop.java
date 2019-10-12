package com.liyan.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Shop {

    @Id
    @GeneratedValue
    private Long shopId;
    private String shopName;
    private Integer weight;
    private String introduce;
    private String icon;
    private String facadeImg;
    private String contactInfo;
    private String address;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer availableState;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> productList;
    //类别ID
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY, targetEntity = ShopCategory.class)
    @JoinColumn(name = "shopCategoryId")
    private ShopCategory shopCategory;
    /**
     * 用户ID
     */
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Orders.class)
    @JoinColumn(name = "ordersId")
    private List<Orders> ordersList;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFacadeImg() {
        return facadeImg;
    }

    public void setFacadeImg(String facadeImg) {
        this.facadeImg = facadeImg;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getAvailableState() {
        return availableState;
    }

    public void setAvailableState(Integer availableState) {
        this.availableState = availableState;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ShopCategory getShopCategory() {
        if (shopCategory == null)
            return new ShopCategory();
        else
            return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    public User getUser() {
        if (user == null)
            return new User();
        else
            return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", weight=" + weight +
                ", introduce='" + introduce + '\'' +
                ", icon='" + icon + '\'' +
                ", facadeImg='" + facadeImg + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", availableState=" + availableState +
                ", productList=" + productList +
                ", shopCategory=" + shopCategory +
                ", user=" + user +
                ", ordersList=" + ordersList +
                '}';
    }
}
