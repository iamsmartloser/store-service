package com.liyan.store.repository;

import com.liyan.store.domain.OrdersProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdersProductRepository extends JpaRepository<OrdersProduct,Long> ,
        PagingAndSortingRepository<OrdersProduct,Long>,JpaSpecificationExecutor<OrdersProduct> {
    public OrdersProduct findByProductAttrDiyAttrId(Long diyAttrId);
}
