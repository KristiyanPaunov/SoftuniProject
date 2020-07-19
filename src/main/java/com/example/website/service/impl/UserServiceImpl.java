package com.example.website.service.impl;

import com.example.website.model.entity.Role;
import com.example.website.model.entity.User;
import com.example.website.model.repository.UserRepository;
import com.example.website.model.service.UserServiceModel;
import com.example.website.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {

        User user = this.modelMapper
                .map(userServiceModel,User.class);

        user.setRole(Role.USER);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);

    }

    @Override
    public UserServiceModel findByUsername(String username) {


        return this.userRepository
                .findByUsername(username)
                .map(user -> this.modelMapper.map(user,UserServiceModel.class))
                .orElse(null);
    }



}
