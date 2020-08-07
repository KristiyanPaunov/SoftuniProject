package com.example.website.model.service;

import com.example.website.model.entity.Cart;
import com.example.website.model.entity.Role;

import javax.persistence.OneToOne;

public class UserServiceModel extends BaseServiceModel{
    private String username;
    private String password;
    private String email;
    private Role roles;
    private CartServiceModel cart;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return roles;
    }

    public void setRole(Role role) {
        this.roles = role;
    }

    public CartServiceModel getCart() {
        return cart;
    }

    public void setCart(CartServiceModel cart) {
        this.cart = cart;
    }
}
