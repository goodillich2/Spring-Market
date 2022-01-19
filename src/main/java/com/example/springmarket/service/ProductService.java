package com.example.springmarket.service;

import com.example.springmarket.model.Product;
import com.example.springmarket.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public Product findById(Long id){
        return productRepository.getById(id);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Product saveProduct(Product user){
        return productRepository.save(user);
    }
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
