package com.example.website.service;

import com.example.website.model.service.CartServiceModel;

import java.math.BigDecimal;

public interface CartService {
    CartServiceModel addProduct(Long id, Long userId);

    void removeProductFromCart(Long id, Long userId);

    void removeAllProductsFromCart(Long id);

    BigDecimal removeAllProductsFromCartAndReturnSum(Long id);

    CartServiceModel getAllProducts(Long cartId);


}
