package com.liyan.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrdersProduct {
    @Id
    @GeneratedValue
    private Long id;
    private Integer number;
    private BigDecimal price;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch= FetchType.LAZY,targetEntity=ProductAttr.class)
    @JoinColumn(name="diyAttrId")
    @JsonIgnore
    private ProductAttr productAttr;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},fetch= FetchType.LAZY,targetEntity=Orders.class)
    @JoinColumn(name = "ordersId")
    private Orders orders;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
