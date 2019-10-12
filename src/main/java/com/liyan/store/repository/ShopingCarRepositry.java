package com.liyan.store.repository;

import com.liyan.store.domain.Product;
import com.liyan.store.domain.ShopingCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ShopingCarRepositry extends JpaRepository<ShopingCar,Long>,PagingAndSortingRepository<ShopingCar,Long>
    ,JpaSpecificationExecutor<ShopingCar>
{
    //查询某一个用户的购物车
    public ShopingCar getByUserUserId(Long userId);


}
