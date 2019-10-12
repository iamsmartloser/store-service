package com.liyan.store.service;

import com.liyan.store.domain.*;
import com.liyan.store.domain.requ.AddOrdersReq;
import com.liyan.store.domain.requ.OrderItem;
import com.liyan.store.domain.requ.OrdersData;
import com.liyan.store.domain.requ.OrdersListReq;
import com.liyan.store.domain.result.OrdersProductResult;
import com.liyan.store.domain.result.OrdersResult;
import com.liyan.store.properties.AddrProoerties;
import com.liyan.store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductAttrRepository productAttrRepository;
    @Autowired
    private OrdersProductRepository ordersProductRepository;
    @Autowired
    private AddrProoerties addrProoerties;

    @Transactional
    public void addOrders(AddOrdersReq addOrdersReq)throws Exception{
        User user=userRepository.getOne(addOrdersReq.getUserId());
        //一个店铺一个订单
        for (OrdersData ordersData:addOrdersReq.getOrdersDataList()){
            Shop shop=shopRepository.getOne(ordersData.getShopId());
            Orders orders=new Orders();
            orders.setUser(user);
            orders.setShop(shop);
            orders.setPersonName(addOrdersReq.getPersonName());
            orders.setPersonTel(addOrdersReq.getPersonTel());
            orders.setPersonAttr(addOrdersReq.getPersonAddr());
            orders.setPostCodes(addOrdersReq.getPostCodes());
            BigDecimal payPrice=new BigDecimal(new BigInteger("0"),2);
            for (OrderItem orderItem:ordersData.getOrderItemList()){

                //OrdersProduct ordersProduct=ordersProductRepository.findByProductAttrDiyAttrId(diyAttrId);
                OrdersProduct ordersProduct=new OrdersProduct();
                ProductAttr productAttr=productAttrRepository.getOne(orderItem.getDiyAttrId());
                ordersProduct.setProductAttr(productAttr);
                ordersProduct.setPrice(productAttr.getPrice());
                ordersProduct.setNumber(orderItem.getNumber());
                ordersProduct.setOrders(orders);
                orders.getOrdersProductList().add(ordersProduct);
                payPrice.add(ordersProduct.getProductAttr().getPrice().multiply(new BigDecimal(
                        String.valueOf(ordersProduct.getNumber()))));
            }
            System.out.println("getOrdersProductList size:"+orders.getOrdersProductList().size());
            orders.setCreateTime(new Timestamp(new Date().getTime()));
            orders.setPayPrice(payPrice);
            orders.setStatus(0);//未付款
            //设置订单号:时间+随机数+用户id
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            String newDate=sdf.format(new Date());
            String s="";
            Random random=new Random();
            for(int i=0;i<3;i++){
                s+=random.nextInt(10);
            }
            //得到一个NumberFormat的实例
            NumberFormat nf = NumberFormat.getInstance();
            //设置是否使用分组
            nf.setGroupingUsed(false);
            //设置最大整数位数
            nf.setMaximumIntegerDigits(6);
            //设置最小整数位数
            nf.setMinimumIntegerDigits(6);
            orders.setNumber(newDate+s+nf.format(addOrdersReq.getUserId()));
            ordersRepository.saveAndFlush(orders);



        }
    }
    @Transactional
    public Object ordersList(OrdersListReq ordersListReq)throws Exception{
        Pageable pageable = new PageRequest(ordersListReq.getPage(), ordersListReq.getSize());
        Page<Orders> ordersPage;
        if (ordersListReq.getStatus()!=-1){
            ordersPage=ordersRepository.findByUserUserIdAndStatusOrderByCreateTime(ordersListReq.getUserId()
                    ,ordersListReq.getStatus(),pageable);
        }else {
            ordersPage=ordersRepository.findByUserUserIdOrderByCreateTime(ordersListReq.getUserId(),pageable);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("totalPages", ordersPage.getTotalPages());
        map.put("totalElements", ordersPage.getTotalElements());
        //封装order列表
        List<OrdersResult> ordersResultList=new ArrayList<>();
        for(Orders orders:ordersPage.getContent()){
            OrdersResult ordersResult=new OrdersResult();
            ordersResult.setNumber(orders.getNumber());//订单号
            ordersResult.setOrdersId(orders.getOrdersId());
            ordersResult.setStatus(orders.getStatus());
            List<OrdersProductResult> ordersProductResultList=new ArrayList<>();
            for (OrdersProduct ordersProduct:orders.getOrdersProductList()){
                ProductAttr attr=ordersProduct.getProductAttr();
                OrdersProductResult childResult=new OrdersProductResult();
                childResult.setDiyAttrId(attr.getDiyAttrId());
                childResult.setDiyAttrName(attr.getDiyAttrName());
                childResult.setNumber(ordersProduct.getNumber());
                childResult.setPrice(ordersProduct.getPrice());
                childResult.setOrdersProductId(ordersProduct.getId());
                childResult.setMainImg(addrProoerties.getAddr() + addrProoerties.getPort()
                        + ordersProduct.getProductAttr().getProduct().getMainImg());
                childResult.setProductName(ordersProduct.getProductAttr().getProduct().getProductName());
                ordersProductResultList.add(childResult);

            }
            ordersResult.setOrdersProductList(ordersProductResultList);
            ordersResultList.add(ordersResult);
        }
        map.put("ordersList", ordersResultList);
        return map;
    }

    @Transactional
    public void updateOrderStatus(Long ordersId,Integer status) throws Exception{
        Orders orders=ordersRepository.getOne(ordersId);
        orders.setStatus(status);
    }
}
