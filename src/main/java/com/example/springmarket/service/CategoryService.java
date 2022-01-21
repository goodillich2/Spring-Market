package com.example.springmarket.service;


import com.example.springmarket.model.Category;
import com.example.springmarket.model.Product;
import com.example.springmarket.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){return categoryRepository.findAll();}
    public Category getById(int id){
        return categoryRepository.getById(id);
    }
    public Category save(Category category){
        return categoryRepository.save(category);
    }
    public void deleteById(int id){
        categoryRepository.deleteById(id);
    }

    public void updateById(int id, Category category){
        Category category1 = categoryRepository.getById(id);
        category1.setId(category.getId());
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        category1.setImageUrl(category.getImageUrl());
        categoryRepository.save(category1);

    }


    public boolean findById(int categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }
}
