package com.example.website.service;

import com.example.website.model.entity.Product;
import com.example.website.model.service.ProductServiceModel;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.List;

public interface ProductService {
    void addItem(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllItems();

    ProductServiceModel findById(Long id);

    void delete(Long id);
}
