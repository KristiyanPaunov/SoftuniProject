package com.example.website.model.binding;

import com.example.website.model.entity.Cart;
import com.example.website.model.entity.Role;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterBindingModelTest {

    private UserRegisterBindingModel userRegisterBindingModel;

    @BeforeEach
    public void createUser(){
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }


    @Test
    void getUsername() {
        this.userRegisterBindingModel.setUsername("kris");
        Assert.assertEquals("kris",this.userRegisterBindingModel.getUsername());
    }

    @Test
    void setUsername() {
        this.userRegisterBindingModel.setUsername("kris");
        Assert.assertEquals("kris",this.userRegisterBindingModel.getUsername());
    }

    @Test
    void getPassword() {
        this.userRegisterBindingModel.setPassword("1234");
        Assert.assertEquals("1234",this.userRegisterBindingModel.getPassword());
    }

    @Test
    void setPassword() {
        this.userRegisterBindingModel.setPassword("1234");
        Assert.assertEquals("1234",this.userRegisterBindingModel.getPassword());
    }

    @Test
    void getConfirmPassword() {
        this.userRegisterBindingModel.setConfirmPassword("1234");
        Assert.assertEquals("1234",this.userRegisterBindingModel.getConfirmPassword());
    }

    @Test
    void setConfirmPassword() {
        this.userRegisterBindingModel.setConfirmPassword("1234");
        Assert.assertEquals("1234",this.userRegisterBindingModel.getConfirmPassword());
    }

    @Test
    void getEmail() {
        this.userRegisterBindingModel.setEmail("az@abv.bg");
        Assert.assertEquals("az@abv.bg",this.userRegisterBindingModel.getEmail());
    }

    @Test
    void setEmail() {
        this.userRegisterBindingModel.setEmail("az@abv.bg");
        Assert.assertEquals("az@abv.bg",this.userRegisterBindingModel.getEmail());
    }

    @Test
    void getRole() {
        Role role = new Role();
        role.setName("admin");
        role.setId(1L);
        this.userRegisterBindingModel.setRole(role);
        Assert.assertEquals("admin",this.userRegisterBindingModel.getRole().getName());
    }

    @Test
    void setRole() {
        Role role = new Role();
        role.setName("admin");
        role.setId(1L);
        this.userRegisterBindingModel.setRole(role);
        Assert.assertEquals("admin",this.userRegisterBindingModel.getRole().getName());
    }

    @Test
    void getCart() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProducts(null);
        this.userRegisterBindingModel.setCart(cart);
        Assert.assertEquals(1L,this.userRegisterBindingModel.getCart().getId().longValue());
    }

    @Test
    void setCart() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProducts(null);
        this.userRegisterBindingModel.setCart(cart);
        Assert.assertEquals(1L,this.userRegisterBindingModel.getCart().getId().longValue());
    }
}