package com.liyan.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ProductImg {
    @Id
    @GeneratedValue
    private Integer imgId;
    private String imgAddr;
    private String imgDesc;
    private Timestamp createTime;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY,targetEntity = Product.class)
    @JoinColumn(name = "productId")
    private Product product;

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Product getProduct() {
        if (product==null)
            return new Product();
        else
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
