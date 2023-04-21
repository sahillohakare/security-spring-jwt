package com.sahil.security.repository;

import com.sahil.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo,Integer> {
    public Optional<UserInfo> findByName(String name);
}
