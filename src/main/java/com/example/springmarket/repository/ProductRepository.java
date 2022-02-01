package com.example.springmarket.repository;

import com.example.springmarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getAllByCategory_Id(int category_id);
}
