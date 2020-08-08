package com.example.website.service.impl;

import com.example.website.model.entity.Category;
import com.example.website.model.entity.Product;
import com.example.website.model.repository.CategoryRepository;
import com.example.website.model.repository.ProductRepository;
import com.example.website.model.service.ProductServiceModel;
import com.example.website.service.CategoryService;
import com.example.website.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addItem(ProductServiceModel productServiceModel) {

        Product product = this.modelMapper
                .map(productServiceModel, Product.class);


        product.setCategory(this.categoryRepository
                .findById(productServiceModel.getCategory().getId()).get());

        this.productRepository.saveAndFlush(product);

    }

    @Override
    public List<ProductServiceModel> findAllItems() {

        List<ProductServiceModel> productServiceModelList = this.productRepository
                .findAll()
                .stream()
                .map(item -> {

                    ProductServiceModel productServiceModel = this.modelMapper.map(item, ProductServiceModel.class);

                    if (!item.isDeleted()) {
                        return productServiceModel;
                    }
                    return null;
                }).collect(Collectors.toList());

        List<ProductServiceModel> productServiceModelListReturn = new ArrayList<>();

        productServiceModelList.forEach(productServiceModel -> {
            if (productServiceModel != null){
                productServiceModelListReturn.add(productServiceModel);
            }
        });

        return productServiceModelListReturn;
    }

    @Override
    public ProductServiceModel findById(Long id) {
        return this.productRepository.findById(id)
                .map(item -> {

                    ProductServiceModel productServiceModel = this.modelMapper
                            .map(item, ProductServiceModel.class);


                    return productServiceModel;
                }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Product product = this.productRepository.findById(id).get();

        product.setDeleted(true);

        this.productRepository.saveAndFlush(product);

    }
}
