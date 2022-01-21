package com.example.springmarket.controller;


import com.example.springmarket.model.Product;
import com.example.springmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public String getProducts(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products" , products);
        return "showAllPoducts";
    }


}
