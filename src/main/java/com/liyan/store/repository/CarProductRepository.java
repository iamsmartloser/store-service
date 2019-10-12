package com.liyan.store.repository;

import com.liyan.store.domain.CartProduct;
import com.liyan.store.domain.ProductAttr;
import com.liyan.store.domain.ShopingCar;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CarProductRepository extends JpaRepository<CartProduct ,Long>,
        PagingAndSortingRepository<CartProduct,Long>,JpaSpecificationExecutor<CartProduct> {

    //public List<CartProduct> findAll();


}
