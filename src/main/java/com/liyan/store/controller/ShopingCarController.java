package com.liyan.store.controller;

import com.liyan.store.domain.CartProduct;
import com.liyan.store.domain.Result;
import com.liyan.store.domain.requ.AddToCartReq;
import com.liyan.store.repository.ShopingCarRepositry;
import com.liyan.store.service.ShopingCarService;
import com.liyan.store.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@RestController
public class ShopingCarController {
    @Autowired
    private ShopingCarRepositry shopingCarRepositry;
    @Autowired
    private ShopingCarService shopingCarService;

    @PostMapping(value = "/shopping_car/add_product")
    @ResponseBody
    private Result<Object> addToCart(@RequestBody AddToCartReq addToCartReq) throws Exception {

        shopingCarService.addToCart(addToCartReq);

        return ResultUtil.success();
    }
    @PostMapping(value = "/shopping_car/delete_product")
    @ResponseBody
    private Result<Object> deleteFromCart(@RequestBody AddToCartReq addToCartReq) throws Exception {

        shopingCarService.deleteFromCart(addToCartReq.getCartProductId());

        return ResultUtil.success();
    }
    @PostMapping(value = "/shopping_car/product_list")
    @ResponseBody
    private Result<Object> carProductList(@RequestBody AddToCartReq addToCartReq) throws Exception {


        return ResultUtil.success(shopingCarService.carProductList(addToCartReq));
    }

    @PostMapping(value = "/shopingcar/test")
    @ResponseBody
    private Result<Object> test(@RequestBody AddToCartReq addToCartReq) throws Exception {


        return ResultUtil.success(shopingCarService.carProductList(addToCartReq));
    }


}
