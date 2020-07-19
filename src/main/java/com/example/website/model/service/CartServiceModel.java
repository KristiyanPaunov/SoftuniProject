package com.example.website.model.service;

import java.util.List;

public class CartServiceModel extends BaseServiceModel {

private List<ProductServiceModel> productServiceModels;

    public CartServiceModel() {
    }

    public List<ProductServiceModel> getProductServiceModels() {
        return productServiceModels;
    }

    public void setProductServiceModels(List<ProductServiceModel> productServiceModels) {
        this.productServiceModels = productServiceModels;
    }
}
