package com.liyan.store.repository;

import com.liyan.store.domain.IdentityApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IdentityApplyRepository extends JpaRepository<IdentityApply,Integer>,
        PagingAndSortingRepository<IdentityApply,Integer> {
        public IdentityApply findByUserId(Long userId);
        public Page<IdentityApply> findByStatus(Pageable pageable, Integer status);
}
