package com.example.website.service.impl;

import com.example.website.model.entity.Category;
import com.example.website.model.entity.Product;
import com.example.website.model.repository.CategoryRepository;
import com.example.website.model.repository.ProductRepository;
import com.example.website.model.service.CategoryServiceModel;
import com.example.website.model.service.ProductServiceModel;
import com.example.website.service.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {


    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductRepository productRepository;

    private Product product;
    private ProductServiceModel productServiceModel;
    private Category category;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.product = new Product() {{
            setId(1L);
            setName("Jeans");
        }};

        this.productServiceModel = new ProductServiceModel(){{
            setId(1L);
            setName("Jeans");
        }};

        this.category = new Category(){{
            setName("Blue");
            setId(1L);
        }};

    }

    @Test
    void addItem() {



    }

    @Test
    void findAllItems() {


    }

    @Test
    void findById() {
        Assert.assertEquals(this.productService.findById(1L),null);
    }

    @Test
    void delete() {
    }
}