package com.example.website.model.binding;

import com.example.website.model.entity.Product;

import java.util.List;

public class CartAddBindingModel {
    private Long id;
    private List<Product> products;

    public CartAddBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
