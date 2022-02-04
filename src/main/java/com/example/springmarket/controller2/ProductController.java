package com.example.springmarket.controller2;


import com.example.springmarket.Dto.ProductDto;
import com.example.springmarket.Dto.cart.AddToCartDto;
import com.example.springmarket.model.Category;
import com.example.springmarket.repository.CategoryRepository;
import com.example.springmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
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

    @GetMapping("/{categoryId}")
    public String  getProductsFromOneCategory(@PathVariable int categoryId, Model model) {
        List<ProductDto> products = productService.getAllProductsFromOneCategory(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("AddToCartDto", new AddToCartDto());
        System.out.println(products);
        return "products";
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

    @GetMapping("/list")
    public String  getAllProducts( Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("AddToCartDto", new AddToCartDto());
        return "adminProducts";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/product/list";
    }

}


