package com.example.website.web;

import com.example.website.exception.WrongCategoryException;
import com.example.website.model.binding.UserLoginBindingModel;
import com.example.website.model.binding.UserRegisterBindingModel;
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


@Controller
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login(Model model)  {

        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }


    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("userLoginBindingModel")
                                    UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            HttpSession httpSession, Model model) throws WrongCategoryException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        UserServiceModel user = this.userService.findByUsername(userLoginBindingModel.getUsername());


        if (user == null || !passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword())) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);

            return "redirect:login";
        }

        httpSession.setAttribute("user", user);




        return "redirect:viewProducts";

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

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

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
            return "redirect:admin-register";
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
