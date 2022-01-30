package com.example.springmarket.controller;

import com.example.springmarket.model.Category;
import com.example.springmarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public String createCategory(@RequestBody Category category){
        categoryService.save(category);
        return "a new category created";
    }

    @GetMapping("/list")
    public List<Category> getList(){
        return categoryService.getAll();
    }

    @PostMapping("/update/{categoryId}")
    public String updateCategory(@RequestBody Category category, @PathVariable("categoryId") int categoryId){
        if(!categoryService.findById(categoryId)){
            return "Category does not exist";
        }
        categoryService.updateById(categoryId, category);
        return "category"+ Integer.toString(categoryId)+" was updated";
    }
}
