package com.liyan.store.repository;

import com.liyan.store.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{

public User findByUserName(String userName);
public User findByAccessToken(String accessToken);
    public User findByUserId(Long userId);
}
