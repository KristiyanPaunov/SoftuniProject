package com.example.website.model.binding;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginBindingModelTest {

    private UserLoginBindingModel userLoginBindingModel;

    @BeforeEach
    public void loginUser(){
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    @Test
    void getUsername() {
        this.userLoginBindingModel.setUsername("kris");
        Assert.assertEquals("kris",this.userLoginBindingModel.getUsername());
    }

    @Test
    void setUsername() {
        this.userLoginBindingModel.setUsername("kris");
        Assert.assertEquals("kris",this.userLoginBindingModel.getUsername());
    }

    @Test
    void getPassword() {
        this.userLoginBindingModel.setPassword("1234");
        Assert.assertEquals("1234",this.userLoginBindingModel.getPassword());
    }

    @Test
    void setPassword() {
        this.userLoginBindingModel.setPassword("1234");
        Assert.assertEquals("1234",this.userLoginBindingModel.getPassword());
    }
}