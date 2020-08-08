package com.example.website.service.impl;

import com.example.website.model.entity.Category;
import com.example.website.model.entity.Product;
import com.example.website.model.repository.CategoryRepository;
import com.example.website.model.service.CategoryServiceModel;
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

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryServiceModel categoryServiceModel;

    @Mock
    private ModelMapper modelMapper;

    private Category testCategory;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.testCategory = new Category() {{
            setId(1L);
            setName("Jeans");

        }};

        this.categoryServiceModel = new CategoryServiceModel(){{
            setId(1L);
            setName("Jeans");
        }};

        categoryService.addCategory(this.categoryServiceModel);
    }


    @Test
    void addCategory() {

        Mockito.when(this.modelMapper
                .map(categoryServiceModel, Category.class))
                .thenReturn(this.testCategory);

        categoryService.addCategory(this.categoryServiceModel);


    }

    @Test
    void findAllCategories() {

        Assert.assertEquals(this.categoryService.findAllCategories().size(),0);

    }

    @Test
    void findById() {

        Assert.assertEquals(this.categoryService.findById(1L),null);

    }

    @Test
    void delete() {

    }

    @Test
    void findByName() {
        Assert.assertEquals(this.categoryService.findByName("Jeans"),null);
    }

    @Test
    void find() {

    }
}