package com.liyan.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @Column(nullable = false)
    @NotBlank(message = "商品名字不能为空")
    private String productName;
    private Integer weight=0;
    @Column(nullable = false)
    private Integer stock;//库存
    private Integer salesVolume=0;//销量
    private String mainImg;
    private String introduce;
    @Column(nullable = false,columnDefinition = "decimal(10,2)")
    private BigDecimal originalPrice;
    private BigDecimal discountPrice;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer state=0;//商品状态：0上架、1下架、2缺货、3失效等，默认上架
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = ProductImg.class)
    private List<ProductImg> productImgs;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = ProductAttr.class)
    private List<ProductAttr> productAttrs;
    //类别ID
    @JsonIgnore
    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY,targetEntity = ProductCategory.class)
    @JoinColumn(name = "productCategoryId")
    private ProductCategory productCategory;
//    @JsonIgnore
//    @ManyToMany(fetch=FetchType.LAZY,targetEntity=Orders.class)
//    @JoinColumn(name = "orders_id")
//    private List<Orders> ordersList;

    //店铺ID
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY, targetEntity = Shop.class)
    @JoinColumn(name = "shopId")
    private Shop shop;


    public List<ProductAttr> getProductAttrs() {
        return productAttrs;
    }

    public void setProductAttrs(List<ProductAttr> productAttrs) {
        this.productAttrs = productAttrs;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<ProductImg> getProductImgs() {
        if(this.productImgs==null)
            return new ArrayList<>();
        else
            return productImgs;
    }

    public void setProductImgs(List<ProductImg> productImgs) {
        this.productImgs = productImgs;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Shop getShop() {
        if(shop==null)
            return new Shop();
        else
            return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

}
