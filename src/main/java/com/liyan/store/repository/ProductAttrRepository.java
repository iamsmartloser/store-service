package com.liyan.store.repository;

import com.liyan.store.domain.ProductAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductAttrRepository extends JpaRepository<ProductAttr,Long>,
        PagingAndSortingRepository<ProductAttr,Long> ,JpaSpecificationExecutor<ProductAttr> {

}
