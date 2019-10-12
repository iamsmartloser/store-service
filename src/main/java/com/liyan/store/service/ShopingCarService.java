package com.liyan.store.service;

import com.liyan.store.domain.CartProduct;
import com.liyan.store.domain.ProductAttr;
import com.liyan.store.domain.Shop;
import com.liyan.store.domain.ShopingCar;
import com.liyan.store.domain.requ.AddToCartReq;
import com.liyan.store.domain.result.CartProductResult;
import com.liyan.store.properties.AddrProoerties;
import com.liyan.store.repository.CarProductRepository;
import com.liyan.store.repository.ProductAttrRepository;
import com.liyan.store.repository.ShopingCarRepositry;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopingCarService {

    @Autowired
    private AddrProoerties addrProoerties;
    @Autowired
    private ShopingCarRepositry shopingCarRepositry;
    @Autowired
    private CarProductRepository carProductRepository;
    @Autowired
    private ProductAttrRepository productAttrRepository;

    @Transactional
    public void addToCart(AddToCartReq addToCartReq) throws Exception {
        ShopingCar shopingCar = shopingCarRepositry.getByUserUserId(addToCartReq.getUserId());
        ProductAttr tempProductAttr = productAttrRepository.getOne(addToCartReq.getDiyAttrId());
        List<CartProduct> productList = shopingCar.getProductList();
        if (productList.size() > 0) {
            for (CartProduct product : productList) {
                if (product.getProductAttr() == tempProductAttr) {
                    product.setNumber(addToCartReq.getNumber()+product.getNumber());
                    shopingCarRepositry.saveAndFlush(shopingCar);
                    return;
                }
            }

        }
        //找出店铺id、名字
        Shop shop=tempProductAttr.getProduct().getShop();

        CartProduct cartProduct = new CartProduct();
        cartProduct.setNumber(addToCartReq.getNumber());
        cartProduct.setShop(shop);
        cartProduct.setProductAttr(tempProductAttr);
        shopingCar.getProductList().add(cartProduct);
        cartProduct.getCarList().add(shopingCar);

        shopingCarRepositry.saveAndFlush(shopingCar);
    }
    @Transactional
    public Object carProductList(AddToCartReq addToCartReq) {
        ShopingCar shopingCar = shopingCarRepositry.getByUserUserId(addToCartReq.getUserId());
        Specification<CartProduct> querySpecifi = new Specification<CartProduct>() {
            @Override
            public Predicate toPredicate(Root<CartProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                ListJoin<CartProduct, ShopingCar> join = root.join(root.getModel().getList("carList", ShopingCar.class)
                        , JoinType.LEFT);
                Predicate p1 = criteriaBuilder.equal(join.get("carId").as(Long.class), shopingCar.getCarId());
                return criteriaQuery
//                        .groupBy(root.get("productAttr").get("product").get("shop"))
                        .orderBy(criteriaBuilder.asc(root.get("productAttr").get("product").get("shop")))
                        .where(p1)
                        .getRestriction();
            }
        };
        List<CartProduct> cartProductList;
        if (addToCartReq.getPage() == null) {
            cartProductList = carProductRepository.findAll(querySpecifi);
            return cartProductList;
        } else {
            Pageable pageable = new PageRequest(addToCartReq.getPage(), addToCartReq.getSize());
            Page<CartProduct> cartProducts = carProductRepository.findAll(querySpecifi, pageable);
            Map<String, Object> map = new HashMap<>();
            map.put("totalPages", cartProducts.getTotalPages());
            map.put("totalElements", cartProducts.getTotalElements());
            List<CartProductResult> resultList = new ArrayList<>();
            for (CartProduct cartProduct : cartProducts.getContent()) {
                CartProductResult result = new CartProductResult();
                result.setShopId(cartProduct.getShop().getShopId());
                result.setShopName(cartProduct.getShop().getShopName());
                result.setNumber(cartProduct.getNumber());
                result.setCartProductId(cartProduct.getCartProductId());
                result.setDiyAttrId(cartProduct.getProductAttr().getDiyAttrId());
                result.setDiyAttrName(cartProduct.getProductAttr().getDiyAttrName());
                result.setPrice(cartProduct.getProductAttr().getPrice());
                result.setProductName(cartProduct.getProductAttr().getProduct().getProductName());
                result.setMainImg(addrProoerties.getAddr() + addrProoerties.getPort()
                        + cartProduct.getProductAttr().getProduct().getMainImg());
                resultList.add(result);
            }
            map.put("cartProductList", resultList);
            return map;
        }
    }
    @Transactional
    public void deleteFromCart(Long cartProductId) throws Exception{
        carProductRepository.deleteById(cartProductId);

    }

}
