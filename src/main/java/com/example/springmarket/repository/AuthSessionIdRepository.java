package com.example.springmarket.repository;

import com.example.springmarket.model.user.AuthSessionId;
import com.example.springmarket.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthSessionIdRepository extends JpaRepository<AuthSessionId, Integer> {
    AuthSessionId findBySessionId(String SessionId);
}
