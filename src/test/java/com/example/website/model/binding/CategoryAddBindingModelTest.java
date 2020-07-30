package com.example.website.model.binding;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryAddBindingModelTest {

    private CategoryAddBindingModel categoryAddBindingModel = new CategoryAddBindingModel();



    @Test
    void getName() {
        categoryAddBindingModel.setName("misho");
        Assert.assertEquals("misho",categoryAddBindingModel.getName());
    }

    @Test
    void setName() {
        categoryAddBindingModel.setName("misho");
        Assert.assertEquals("misho",categoryAddBindingModel.getName());
    }

    @Test
    void getId() {
        categoryAddBindingModel.setId(1L);
        Assert.assertEquals(1L,categoryAddBindingModel.getId().longValue());
    }

    @Test
    void setId() {
        categoryAddBindingModel.setId(1L);
        Assert.assertEquals(1L,categoryAddBindingModel.getId().longValue());
    }
}