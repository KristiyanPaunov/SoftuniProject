package com.example.website.service.impl;

import com.example.website.model.entity.Cart;
import com.example.website.model.entity.Role;
import com.example.website.model.entity.User;
import com.example.website.model.repository.CartRepository;
import com.example.website.model.repository.RoleRepository;
import com.example.website.model.repository.UserRepository;
import com.example.website.model.service.UserServiceModel;
import com.example.website.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CartRepository cartRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.cartRepository = cartRepository;
        this.roleRepository = roleRepository;
    }


    private UserServiceModel register(UserServiceModel userServiceModel, Role role) {
        Cart cartToDb = new Cart();
        this.cartRepository.saveAndFlush(cartToDb);

        User user = this.modelMapper
                .map(userServiceModel, User.class);
        user.setCart(cartToDb);

        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(role)));


        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel userRegister(UserServiceModel userServiceModel) {

        return register(userServiceModel, this.roleRepository.findByName("USER").get());
    }

    @Override
    public UserServiceModel adminRegister(UserServiceModel userServiceModel) {
        return   register(userServiceModel, this.roleRepository.findByName("ADMIN").get());
    }

    @Override
    public UserServiceModel findByUsername(String username) {

        User user = userRepository.findByUsername(username).get();
        Role role = user.getRoles().iterator().next();
        UserServiceModel userServiceModel = modelMapper.map(user,UserServiceModel.class);
        userServiceModel.setRole(role);

        return userServiceModel;
    }


}
