package com.example.website.web;

import com.example.website.model.binding.UserRegisterBindingModel;
import com.example.website.model.service.UserServiceModel;
import com.example.website.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
public class UsersController {

    @Autowired
    private  UserService userService;
    @Autowired
    private  ModelMapper modelMapper;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterBindingModel userRegisterBindingModel){

        if (userRegisterBindingModel.getRole() != null){
            return new ResponseEntity<>("Cannot set role!" , HttpStatus.FORBIDDEN);
        }

        userService.register(this.modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class));

        return new ResponseEntity<>(userRegisterBindingModel, HttpStatus.OK);

    }

    @PostMapping("/adminRegister")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity adminRegister(@RequestBody UserRegisterBindingModel userRegisterBindingModel){

        if (userRegisterBindingModel.getRole() != null){
            return new ResponseEntity<>("Cannot set role!" , HttpStatus.FORBIDDEN);
        }


        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));


        return new ResponseEntity<>(userRegisterBindingModel, HttpStatus.CREATED);

    }

    @GetMapping("/view")
    public ResponseEntity viewUsers(){
        return new ResponseEntity<>("Kris",HttpStatus.OK);
    }




}
