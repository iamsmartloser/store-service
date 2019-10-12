package com.liyan.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue
    private Long ordersId;
    private String number;//单号
    private BigDecimal payPrice;//实际支付价可以不要
    private Integer payState;//支付状态，可以不要
    private Timestamp payTime;
    private Integer receivingState;//收货状态
    private Timestamp shipTime;//发货时间
    private Timestamp receivingTime;
    private String shipNumber;//快递单号
    private Integer status;//记录状态0:待付款 1：待发货 2：待收货 3：待评价 4：退款中 4：交易完成 5：交易关闭
    private Timestamp createTime;
    private Timestamp updateTime;
    //以下时收货信息，暂不维护地址表，因为本项目暂时没用到，也不用单独维护收货地址表
    private String personName;
    private String personTel;
    private String personAttr;
    private String postCodes;

    //关联用户 ID
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST}, fetch = FetchType.LAZY, targetEntity = OrdersProduct.class)
    private List<OrdersProduct> ordersProductList;

    /*
    关联店铺id，想了很久，想设计那种一个订单里面的商品只能属于一个店铺的
     ，如果有用户下单时同时有多个店铺的商品，则根据店铺分开生成多个订单
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Shop.class)
    @JoinColumn(name = "shopId")
    private Shop shop;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<OrdersProduct> getOrdersProductList() {
        if (ordersProductList==null){
            ordersProductList=new ArrayList<>();
        }

        return ordersProductList;
    }

    public void setOrdersProductList(List<OrdersProduct> ordersProductList) {
        this.ordersProductList = ordersProductList;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Integer getReceivingState() {
        return receivingState;
    }

    public void setReceivingState(Integer receivingState) {
        this.receivingState = receivingState;
    }

    public Timestamp getShipTime() {
        return shipTime;
    }

    public void setShipTime(Timestamp shipTime) {
        this.shipTime = shipTime;
    }

    public Timestamp getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Timestamp receivingTime) {
        this.receivingTime = receivingTime;
    }

    public String getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public User getUser() {
        if (user == null)
            return new User();
        else
            return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shop getShop() {
        if (shop == null)
            return new Shop();
        else
            return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonTel() {
        return personTel;
    }

    public void setPersonTel(String personTel) {
        this.personTel = personTel;
    }

    public String getPersonAttr() {
        return personAttr;
    }

    public void setPersonAttr(String personAttr) {
        this.personAttr = personAttr;
    }

    public String getPostCodes() {
        return postCodes;
    }

    public void setPostCodes(String postCodes) {
        this.postCodes = postCodes;
    }
}
