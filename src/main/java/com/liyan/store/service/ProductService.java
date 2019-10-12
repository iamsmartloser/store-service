package com.liyan.store.service;

import com.liyan.store.domain.Product;
import com.liyan.store.domain.ProductAttr;
import com.liyan.store.domain.ProductImg;
import com.liyan.store.domain.Shop;
import com.liyan.store.domain.requ.UploadProduct;
import com.liyan.store.domain.result.ProductDetail;
import com.liyan.store.domain.result.ProductResult;
import com.liyan.store.enums.ResultEnum;
import com.liyan.store.exception.ReqException;
import com.liyan.store.properties.AddrProoerties;
import com.liyan.store.repository.ProductCategoryRepository;
import com.liyan.store.repository.ProductImgRepository;
import com.liyan.store.repository.ProductRepository;
import com.liyan.store.repository.ShopRepository;
import com.liyan.store.utils.ImgSaveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private AddrProoerties addrProoerties;
    @Autowired
    private ProductImgRepository productImgRepository;
    @Autowired
    private ShopRepository shopRepository;

    /**
     * 上传商品
     */
    @Transactional
    public void uploadProduct(UploadProduct uploadProduct) throws Exception {
        Shop shop = shopRepository.findByUserUserId(uploadProduct.getUserId());
        Product product = new Product();
        ImgSaveUtil imgSaveUtil = new ImgSaveUtil();
        String path = "G:/ideaCode/work/pic/";
        //存储主图小图，方便前端列表显示,在前端提交前压缩成100*100的
        System.out.println("" + shop.toString());
        String iconName = imgSaveUtil.saveImg(shop.getShopId().toString() + new Date().getTime(), uploadProduct.getMainImg(), path);
        product.setMainImg("/store/pic/" + iconName);
        //存储商品图片列表
        List<ProductImg> productImgs = new ArrayList<>();
        int i = 0;
        for (ProductImg productImg : uploadProduct.getProductImgs()) {
            ProductImg temp = new ProductImg();
            long date = new Date().getTime();
            temp.setCreateTime(new Timestamp(date));
            String iconNameTemp = imgSaveUtil.saveImg(shop.getShopName() + date + i, productImg.getImgAddr(), path);
            temp.setImgAddr("/store/pic/" + iconNameTemp);
            temp.setProduct(product);
            productImgs.add(temp);
            i++;
        }

        product.setProductImgs(productImgs);
        //存储自定义属性价格表
        Integer stock=0;
        for (ProductAttr productAttr : uploadProduct.getProductAttrs()) {
            productAttr.setProduct(product);
            stock=stock+productAttr.getStock();
        }

        product.setProductAttrs(uploadProduct.getProductAttrs());
        product.setProductName(uploadProduct.getProductName());
        product.setShop(shop);
        Timestamp timestamp=new Timestamp(new Date().getTime());
        product.setCreateTime(timestamp);
        product.setUpdateTime(timestamp);
        product.setIntroduce(uploadProduct.getIntroduce());
        product.setOriginalPrice(uploadProduct.getOriginalPrice());//这里的价格为最低属性价格
        product.setStock(stock);
        product.setProductCategory(productCategoryRepository.getOne(uploadProduct.getProductCategoryId()));

        productRepository.saveAndFlush(product);
    }

    /**
     * 商品查询，标题关键字查询，分类查询
     * 这里规定两个参数必有一空
     */
    @Transactional
    public Object findProduct(List<String> productName, Integer categoryId, Pageable pageable) throws Exception {
        Page<Product> products;
        Map<String, Object> map = new HashMap<>();
//        if (null == productName && null == categoryId){
//            throw new ReqException(ResultEnum.PARAMETER_ERROR);
//        }

        Specification querySpecifi = new Specification<Product>() {
            List<Predicate> predicates = new ArrayList<>();//or
            List<Predicate> predicates2 = new ArrayList<>();//and
            Predicate temp = null;

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                if (0 != categoryId) {
                    predicates2.add(criteriaBuilder.equal(root.get("productCategory").get("productCategoryId"), categoryId));
                }
                if (null != productName) {
                    for (String s : productName) {
                        Predicate predicate = criteriaBuilder.like(root.get("productName"), "%" + s + "%");
                        predicates.add(predicate);
                    }
                    System.out.println("predicates.size::::" + predicates.size());
                    temp = criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
                }
                if (null != temp) {
                    predicates2.add(temp);
                }
                System.out.println("predicates2.size::::" + predicates2.size());
                return criteriaBuilder.and(predicates2.toArray(new Predicate[predicates2.size()]));
            }
        };
        products = productRepository.findAll(querySpecifi, pageable);
        map.put("totalPages", products.getTotalPages());
        map.put("totalElements", products.getTotalElements());
        List<Product> productList = products.getContent();
        List<ProductResult> productResults = new ArrayList<>();
        for (Product p : productList) {
            ProductResult result = new ProductResult();
            result.setProductId(p.getProductId());
            result.setProductName(p.getProductName());
            result.setMainImg(addrProoerties.getAddr() + addrProoerties.getPort() + p.getMainImg());
            result.setSalesVolume(p.getSalesVolume());
            result.setOriginalPrice(p.getOriginalPrice());
            result.setDiscountPrice(p.getDiscountPrice());
            productResults.add(result);
        }
        map.put("productList", productResults);
        return map;
    }
    /**
     * 商品详情
     */
    @Transactional
    public ProductDetail productDetail(Long productId) throws Exception {

        ProductDetail productDetail=new ProductDetail();
        Product product = productRepository.findByProductId(productId);
        if (product == null){
            throw new ReqException(ResultEnum.PRODUCT_NOT_FIND);
        }else {
            productDetail.setProductId(product.getProductId());
            productDetail.setProductName(product.getProductName());
            productDetail.setCreateTime(product.getCreateTime().toString());
            productDetail.setDiscountPrice(product.getDiscountPrice());
            productDetail.setOriginalPrice(product.getOriginalPrice());
            productDetail.setIntroduce(product.getIntroduce());
            productDetail.setMainImg(addrProoerties.getAddr() + addrProoerties.getPort()+product.getMainImg());
            productDetail.setProductAttrs(product.getProductAttrs());
            List<ProductImg> productImgList=new ArrayList<>();
            for (ProductImg img:product.getProductImgs()){
                ProductImg productImg=new ProductImg();
                productImg.setImgAddr(addrProoerties.getAddr() + addrProoerties.getPort()+img.getImgAddr());
                productImg.setImgId(img.getImgId());
                productImg.setCreateTime(img.getCreateTime());
                productImg.setImgDesc(img.getImgDesc());
                productImgList.add(productImg);
            }
            productDetail.setProductImgs(productImgList);
            System.out.println("productImgList.size():"+productImgList.size()+"   product.getProductImgs():"+product.getProductImgs().size());
            productDetail.setProductCategory(product.getProductCategory());
            productDetail.setSalesVolume(product.getSalesVolume());
            productDetail.setState(product.getState());
            productDetail.setStock(product.getStock());
            productDetail.setUpdateTime(product.getUpdateTime().toString());
            productDetail.setUserId(product.getShop().getUser().getUserId());
            productDetail.setUserName(product.getShop().getUser().getUserName());
        }
            return productDetail;
    }


    @Transactional
    public void test(Pageable pageable) {
        System.out.println("test()");
    }
}
