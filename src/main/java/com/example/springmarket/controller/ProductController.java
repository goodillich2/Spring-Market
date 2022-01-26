package com.example.springmarket.controller;


import com.example.springmarket.Dto.ProductDto;
import com.example.springmarket.model.Category;
import com.example.springmarket.model.Product;
import com.example.springmarket.repository.CategoryRepository;
import com.example.springmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepo;

    @PostMapping("/add")
    public String  createProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return "category does not exists";
        }
        productService.createProduct(productDto, optionalCategory.get());
        return "product has been added";
    }

    @GetMapping("/")
    public List<ProductDto> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return products;
    }

    // create an api to edit the product


    @PostMapping("/update/{productId}")
    public String  updateProduct(@PathVariable("productId") int productId, @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return "category does not exist";
        }
        productService.updateProduct(productDto, productId);
        return "product has been updated";
    }

}


