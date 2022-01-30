package com.example.springmarket.repository;

import com.example.springmarket.model.Cart;
import com.example.springmarket.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
