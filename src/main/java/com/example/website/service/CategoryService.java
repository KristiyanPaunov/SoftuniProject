package com.example.website.service;

import com.example.website.model.entity.Category;
import com.example.website.model.service.CategoryServiceModel;
import com.example.website.model.service.ProductServiceModel;

import java.util.List;

public interface CategoryService {
    void addCategory(CategoryServiceModel categoryServiceModel);

    List<CategoryServiceModel> findAllCategories();

    CategoryServiceModel findById(Long id);

    void delete(Long id);

    CategoryServiceModel findByName(String categoryName);

    Category find(String categoryName);
}
