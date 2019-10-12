package com.liyan.store.repository;

import com.liyan.store.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> ,
        PagingAndSortingRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    public Page<Product> findByProductCategoryProductCategoryId(Integer categoryId,Pageable pageable);

    public Product findByProductId(Long productId);

}
