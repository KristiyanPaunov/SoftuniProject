package com.example.website.service.impl;

import com.example.website.model.entity.Category;
import com.example.website.model.entity.Product;
import com.example.website.model.repository.CategoryRepository;
import com.example.website.model.repository.ProductRepository;
import com.example.website.model.service.CategoryServiceModel;
import com.example.website.model.service.ProductServiceModel;
import com.example.website.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void addCategory(CategoryServiceModel categoryServiceModel) {

        Category category = this.modelMapper
                .map(categoryServiceModel, Category.class);

        this.categoryRepository.saveAndFlush(category);

    }

    @Override
    public List<CategoryServiceModel> findAllCategories() {
        return this.categoryRepository
                .findAll()
                .stream()
                .map(category -> {
                    CategoryServiceModel categoryServiceModel = this.modelMapper.map(category, CategoryServiceModel.class);

                    return categoryServiceModel;
                }).collect(Collectors.toList());
    }

    @Override
    public CategoryServiceModel findById(Long id) {
        return this.categoryRepository.findById(id)
                .map(item -> {
                    CategoryServiceModel categoryServiceModel = this.modelMapper
                            .map(item, CategoryServiceModel.class);


                    return categoryServiceModel;
                }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public CategoryServiceModel findByName(String categoryName) {

        return this.categoryRepository.findByName(categoryName)
                .map(category -> this.modelMapper.map(category,CategoryServiceModel.class))
                .orElse(null);

    }

    @Override
    public Category find(String categoryName) {
        return this.categoryRepository
                .findByName(categoryName)
                .orElse(null);
    }


}
