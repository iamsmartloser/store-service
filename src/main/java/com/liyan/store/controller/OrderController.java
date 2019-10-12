package com.liyan.store.controller;

import com.liyan.store.domain.Result;
import com.liyan.store.domain.requ.AddOrdersReq;
import com.liyan.store.domain.requ.OrdersListReq;
import com.liyan.store.service.OrderService;
import com.liyan.store.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order/add")
    @ResponseBody
    private Result<Object> addOrders(@RequestBody AddOrdersReq addOrdersReq) throws Exception {

        orderService.addOrders(addOrdersReq);
        return ResultUtil.success();
    }

    @PostMapping(value = "/order/list")
    @ResponseBody
    private Result<Object> ordersList(@RequestBody OrdersListReq ordersListReq) throws Exception{

        return ResultUtil.success(orderService.ordersList(ordersListReq));
    }
    @PostMapping(value = "/order/status/update")
    @ResponseBody
    private Result<Object> updateOrderStatus(@RequestBody Map<String,Object> params)throws Exception{
        Long ordersId=Long.getLong(params.get("ordersId").toString());
        Integer status=Integer.valueOf(params.get("status").toString());
        orderService.updateOrderStatus(ordersId,status);
        return ResultUtil.success();
    }

}
