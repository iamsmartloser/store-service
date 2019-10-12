package com.liyan.store.repository;

import com.liyan.store.domain.ShopCategory;
import com.liyan.store.domain.ShopingCar;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface ShopCategoryRepository extends JpaRepository<ShopCategory,Integer> {



}
