package com.example.website.model.binding;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class ProductAddBindingModelTest {
    private ProductAddBindingModel productAddBindingModel;

    @BeforeEach
    public void createProduct(){
        this.productAddBindingModel = new ProductAddBindingModel();
    }

    @Test
    void getName() {
        productAddBindingModel.setName("mlqko");
        Assert.assertEquals("mlqko",productAddBindingModel.getName());
    }

    @Test
    void setName() {
        productAddBindingModel.setName("mlqko");
        Assert.assertEquals("mlqko",productAddBindingModel.getName());
    }

    @Test
    void getGender() {
        productAddBindingModel.setGender("male");
        Assert.assertEquals("male",productAddBindingModel.getGender());
    }

    @Test
    void setGender() {
        productAddBindingModel.setGender("male");
        Assert.assertEquals("male",productAddBindingModel.getGender());
    }

    @Test
    void getDescription() {
        productAddBindingModel.setDescription("as");
        Assert.assertEquals("as",productAddBindingModel.getDescription());
    }

    @Test
    void setDescription() {
        productAddBindingModel.setDescription("as");
        Assert.assertEquals("as",productAddBindingModel.getDescription());
    }

    @Test
    void getPrice() {
        productAddBindingModel.setPrice(new BigDecimal(3));
        Assert.assertEquals(new BigDecimal(3),productAddBindingModel.getPrice());
    }

    @Test
    void setPrice() {
        productAddBindingModel.setPrice(new BigDecimal(3));
        Assert.assertEquals(new BigDecimal(3),productAddBindingModel.getPrice());
    }

    @Test
    void getCategoryAddBindingModel() {
        CategoryAddBindingModel categoryAddBindingModel = new CategoryAddBindingModel();
        productAddBindingModel.setCategoryAddBindingModel(categoryAddBindingModel);
        Assert.assertEquals(categoryAddBindingModel,productAddBindingModel.getCategoryAddBindingModel());
    }

    @Test
    void setCategoryAddBindingModel() {
        CategoryAddBindingModel categoryAddBindingModel = new CategoryAddBindingModel();
        productAddBindingModel.setCategoryAddBindingModel(categoryAddBindingModel);
        Assert.assertEquals(categoryAddBindingModel,productAddBindingModel.getCategoryAddBindingModel());
    }

    @Test
    void isDeleted() {
        productAddBindingModel.setDeleted(true);
        Assert.assertEquals(true,productAddBindingModel.isDeleted());
    }

    @Test
    void setDeleted() {
        productAddBindingModel.setDeleted(true);
        Assert.assertEquals(true,productAddBindingModel.isDeleted());
    }
}