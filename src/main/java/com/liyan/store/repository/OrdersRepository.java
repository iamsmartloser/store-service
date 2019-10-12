package com.liyan.store.repository;

import com.liyan.store.domain.Orders;
import com.liyan.store.domain.OrdersProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdersRepository extends JpaRepository<Orders,Long>,
        PagingAndSortingRepository<Orders,Long>,JpaSpecificationExecutor<Orders> {
    public Page<Orders> findByUserUserIdAndStatusOrderByCreateTime(Long userId,Integer status,Pageable pageable);
    public Page<Orders> findByUserUserIdOrderByCreateTime(Long userId,Pageable pageable);
}
