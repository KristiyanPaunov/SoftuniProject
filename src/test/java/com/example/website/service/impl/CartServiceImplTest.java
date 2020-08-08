package com.example.website.service.impl;

import com.example.website.model.entity.Cart;
import com.example.website.model.entity.Product;
import com.example.website.model.entity.User;
import com.example.website.model.repository.CartRepository;
import com.example.website.model.repository.ProductRepository;
import com.example.website.model.repository.UserRepository;
import com.example.website.model.service.CartServiceModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CartServiceImplTest {


    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartServiceModel cartServiceModel;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CartRepository cartRepository;

    private Product testProduct;
    private User testUser;
    private Cart testCart;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.testProduct = new Product() {{
            setId(1L);
            setName("Potnik");
            setDeleted(false);
            setPrice(new BigDecimal(35.57));
        }};

        List<Product> productList = new ArrayList<>();
        productList.add(this.testProduct);

        this.testCart = new Cart() {{
            setId(3L);
            setProducts(productList);
        }};

        this.testUser = new User() {{
            setId(2L);
            setCart(testCart);
        }};

        this.userRepository.save(testUser);
        this.productRepository.save(testProduct);
        this.cartRepository.save(testCart);
    }

    @Test
    void addProduct() {

        Optional<User> userOptional = Optional.of(this.testUser);
        Optional<Cart> cartOptional = Optional.of(this.testCart);
        Optional<Product> productOptional = Optional.of(this.testProduct);

        Product product = this.testProduct;

        System.out.println();

        Mockito.when(this.cartRepository.findById(1L))
                .thenReturn(cartOptional);

        Mockito.when(this.userRepository.findById(3L))
                .thenReturn(userOptional);

        Mockito.when(this.productRepository.findById(1L))
                .thenReturn(productOptional);

        this.cartService.addProduct(1L,3L);


        Assert.assertEquals(this.testUser.getCart().getProducts().size(),1);

    }

    @Test
    void removeProductFromCart() {

        Optional<User> userOptional = Optional.of(this.testUser);
        Optional<Product> productOptional = Optional.of(this.testProduct);


        Mockito.when(this.productRepository.findById(1L))
                .thenReturn(productOptional);

        Mockito.when(this.userRepository.findById(3L))
                .thenReturn(userOptional);

        this.cartService.removeProductFromCart(1L,3L);

        Assert.assertEquals(this.testUser.getCart().getProducts(),new ArrayList<>());


    }

    @Test
    void removeAllProductsFromCart() {
        Optional<User> userOptional = Optional.of(this.testUser);

        Mockito.when(this.userRepository.findById(2L)).thenReturn(userOptional);
        this.cartService.removeAllProductsFromCart(2L);

        Assert.assertEquals(this.testUser.getCart().getProducts(),new ArrayList<>());

    }

    @Test
    void removeAllProductsFromCartAndReturnSum() {

        Optional<User> userOptional = Optional.of(this.testUser);

        Mockito.when(this.userRepository.findById(2L)).thenReturn(userOptional);

        Assert.assertEquals(this.cartService.removeAllProductsFromCartAndReturnSum(2L),new BigDecimal(35.57));


    }

    @Test
    void getAllProducts() {
    }
}