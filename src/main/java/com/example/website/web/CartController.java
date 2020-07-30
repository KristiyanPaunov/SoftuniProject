package com.example.website.web;

import com.example.website.model.binding.CartAddBindingModel;
import com.example.website.model.binding.CategoryAddBindingModel;
import com.example.website.model.binding.ProductAddBindingModel;
import com.example.website.model.entity.Cart;
import com.example.website.model.entity.User;
import com.example.website.model.repository.CartRepository;
import com.example.website.model.service.CartServiceModel;
import com.example.website.model.service.CategoryServiceModel;
import com.example.website.model.service.ProductServiceModel;
import com.example.website.model.service.UserServiceModel;
import com.example.website.service.CartService;
import com.example.website.service.CategoryService;
import com.example.website.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class CartController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;


    @GetMapping("/cart")
    public String returnCart() {


        return "cart";
    }

    @PostMapping("/addProductToCart/{id}")
    public String addProductToCart(@PathVariable("id") Long id, HttpSession httpSession) {

        //works with hardcoded user id but throws exception

        UserServiceModel userServiceModel = (UserServiceModel) httpSession.getAttribute("user");

        CartServiceModel cartServiceModel = this.cartService
                .addProduct(id, userServiceModel.getId());


        //return product

        return "redirect:products-view";

    }


    @GetMapping("/removeProductFromCart/{id}")
    public @ResponseBody
    ResponseEntity removeProductFromCart(@PathVariable("id") Long id, HttpSession httpSession) {

        //works with hardcoded cart id

        UserServiceModel userServiceModel = (UserServiceModel) httpSession.getAttribute("user");

        cartService.removeProductFromCart(id, userServiceModel.getId());

        return new ResponseEntity<>("DELETE Response for cart", HttpStatus.OK);

    }

    @GetMapping("/removeAllProductsFromCart")
    public @ResponseBody
    ResponseEntity removeAllProductsFromCart(HttpSession httpSession) {

        UserServiceModel userServiceModel = (UserServiceModel) httpSession.getAttribute("user");

        cartService.removeAllProductsFromCart(userServiceModel.getId());

        return new ResponseEntity<>("DELETE Response for cart", HttpStatus.OK);

    }

    @GetMapping("/removeAllProductsFromCartAndReturnPrice")
    public @ResponseBody
    ResponseEntity removeAllProductsFromCartAndReturnPrice(HttpSession httpSession) {

        UserServiceModel userServiceModel = (UserServiceModel) httpSession.getAttribute("user");

        BigDecimal sum = cartService.removeAllProductsFromCartAndReturnSum(userServiceModel.getId());

        return new ResponseEntity<>(sum, HttpStatus.OK);

    }


}
