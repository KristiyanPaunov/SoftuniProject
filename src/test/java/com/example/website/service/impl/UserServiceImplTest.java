package com.example.website.service.impl;

import com.example.website.model.entity.Cart;
import com.example.website.model.entity.Role;
import com.example.website.model.repository.CartRepository;
import com.example.website.model.repository.RoleRepository;
import com.example.website.model.repository.UserRepository;
import com.example.website.model.service.UserServiceModel;
import com.example.website.service.UserService;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private RoleRepository roleRepository;

    private UserServiceModel userServiceModel;
    private Role role;

    @BeforeEach
    public void init(){

        MockitoAnnotations.initMocks(this);
        this.userServiceModel = new UserServiceModel(){{
            setId(1L);
            setUsername("Pesho");
            setPassword("1234");
            setEmail("pesho@abv.bg");

        }};

        this.role = new Role(){{
            setId(1L);
            setName("USER");
        }};

    }

    @Test
    void userRegister() {

    }

    @Test
    void adminRegister() {
    }

    @Test
    void findByUsername() {
    }
}