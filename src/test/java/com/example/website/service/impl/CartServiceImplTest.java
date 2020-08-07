package com.example.website.service.impl;

import com.example.website.model.entity.Cart;
import com.example.website.model.entity.Product;
import com.example.website.model.entity.User;
import com.example.website.model.repository.CartRepository;
import com.example.website.model.repository.ProductRepository;
import com.example.website.service.CartService;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
//
//@MockitoJUnitRunner
class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private ProductRepository productRepository;

    private CartRepository cartRepository;

    private Product testProduct;
    private User testUser;
    private Cart testCart;


    @BeforeEach
    public void init(){


        this.testProduct = new Product(){{
            setId(1L);
            setName("Potnik");
            setDeleted(false);
        }};

        List<Product> productList = new ArrayList<>();

        this.testCart = new Cart(){{
            setId(3L);
            setProducts(productList);
        }};

        this.testUser = new User(){{
            setId(2L);
            setCart(testCart);
        }};

        this.cartService = Mockito.mock(CartServiceImpl.class);
        this.cartRepository = Mockito.mock(CartRepository.class);
    }

    @Test
    void addProduct() {
        this.cartService.addProduct(1L,2L);

        Product product = this.cartRepository.findById(3L).get().getProducts().get(0);

        System.out.println();

        Assert.assertEquals(this.cartRepository.findById(3L).get().getProducts().get(0),testProduct);
    }

    @Test
    void removeProductFromCart() {


        Assert.assertEquals(this.cartService.getAllProducts(3L),null);

    }

    @Test
    void removeAllProductsFromCart() {
    }

    @Test
    void removeAllProductsFromCartAndReturnSum() {
    }

    @Test
    void getAllProducts() {
    }
}