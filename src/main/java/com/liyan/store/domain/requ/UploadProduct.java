package com.liyan.store.domain.requ;

import com.liyan.store.domain.ProductAttr;
import com.liyan.store.domain.ProductImg;

import java.math.BigDecimal;
import java.util.List;

public class UploadProduct {
    private Long userId;
    private String productName;
    private Integer stock;//库存
    private Integer salesVolume;//销量
    private String mainImg;
    private String introduce;
    private BigDecimal originalPrice;
    private BigDecimal discountPrice;
    private String createTime;  //上架时间
    private String updateTime;  //更新时间
    private Integer state;//商品状态：缺货、失效、上架、下架等
    private Integer productCategoryId;
    private List<ProductImg> productImgs;
    private List<ProductAttr> productAttrs;

    public List<ProductAttr> getProductAttrs() {
        return productAttrs;
    }

    public void setProductAttrs(List<ProductAttr> productAttrs) {
        this.productAttrs = productAttrs;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<ProductImg> getProductImgs() {
        return productImgs;
    }

    public void setProductImgs(List<ProductImg> productImgs) {
        this.productImgs = productImgs;
    }

    @Override
    public String toString() {
        return "UploadProduct{" +
                "userId=" + userId +
                ", productName='" + productName + '\'' +
                ", stock=" + stock +
                ", salesVolume=" + salesVolume +
                ", mainImg='" + mainImg + '\'' +
                ", introduce='" + introduce + '\'' +
                ", originalPrice=" + originalPrice +
                ", discountPrice=" + discountPrice +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", state=" + state +
                ", productCategoryId=" + productCategoryId +
                ", productImgs=" + productImgs +
                ", productAttrs=" + productAttrs +
                '}';
    }
}
