package com.example.website.web;

import com.example.website.model.binding.UserLoginBindingModel;
import com.example.website.model.binding.UserRegisterBindingModel;
import com.example.website.model.entity.User;
import com.example.website.model.repository.UserRepository;
import com.example.website.model.service.UserServiceModel;
import com.example.website.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;


@Controller
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/login")
    public String login(Model model)  {

        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";

    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegisterBindingModel")
                                   UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        User user = modelMapper.map(userRegisterBindingModel,User.class);



        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())
        || user  != null) {
            
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        this.userService.userRegister(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";

    }

    @GetMapping("/adminRegister")
    public String adminRegister(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "admin-register";

    }

    @PostMapping("/adminRegister")
    public String adminRegister(@Valid @ModelAttribute("userRegisterBindingModel")
                                            UserRegisterBindingModel userRegisterBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (userRegisterBindingModel.getRole() != null) {
            return "redirect:adminRegister";
        }

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:viewProducts";
        }


        this.userService.adminRegister(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));


        return "redirect:viewProducts";

    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/index";
    }

}
