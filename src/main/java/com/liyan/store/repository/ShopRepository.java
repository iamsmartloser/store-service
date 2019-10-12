package com.liyan.store.repository;

import com.liyan.store.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    public Shop findByUserUserId(Long userId);

}
