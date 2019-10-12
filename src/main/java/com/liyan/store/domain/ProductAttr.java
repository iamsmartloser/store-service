package com.liyan.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductAttr {
    @Id
    @GeneratedValue
    private Long diyAttrId;
    private String diyAttrName;
    private BigDecimal price;
    private Integer stock;//库存
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,targetEntity = Product.class)
    @JoinColumn(name = "productId")
    private Product product;
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch= FetchType.LAZY,targetEntity=CartProduct.class)
    @JoinColumn(name="cartProductId")
    private CartProduct cartProduct;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Orders.class)
    private List<Orders> ordersList;
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch= FetchType.LAZY,targetEntity=OrdersProduct.class)
    @JoinColumn(name="ordersId")
    private OrdersProduct ordersProduct;

    public OrdersProduct getOrdersProduct() {
        return ordersProduct;
    }

    public void setOrdersProduct(OrdersProduct ordersProduct) {
        this.ordersProduct = ordersProduct;
    }

    public List<Orders> getOrdersList() {
        if (ordersList==null)
            ordersList=new ArrayList<>();
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public CartProduct getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(CartProduct cartProduct) {
        this.cartProduct = cartProduct;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
