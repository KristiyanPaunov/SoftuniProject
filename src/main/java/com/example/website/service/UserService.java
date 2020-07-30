package com.example.website.service;

import com.example.website.model.entity.User;
import com.example.website.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel userRegister(UserServiceModel userServiceModel);
    UserServiceModel adminRegister(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);
}
