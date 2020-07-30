package com.example.website.web;

import com.example.website.model.binding.ProductAddBindingModel;
import com.example.website.model.binding.UserRegisterBindingModel;
import com.example.website.model.entity.Category;
import com.example.website.model.entity.Product;
import com.example.website.model.repository.CartRepository;
import com.example.website.model.repository.ProductRepository;
import com.example.website.model.service.ProductServiceModel;
import com.example.website.model.service.UserServiceModel;
import com.example.website.service.CartService;
import com.example.website.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {


    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    protected CartRepository cartRepository;


    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody ProductAddBindingModel productAddBindingModel) {

        this.productService
                .addItem(this.modelMapper
                        .map(productAddBindingModel, ProductServiceModel.class));

        Product product = this.modelMapper.map(productAddBindingModel,Product.class);
        String categoryName = product.getCategory().getName();
        product.getCategory().setName(product.getCategory().getName());

// da smenq metod name i da returrna mapped entity
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @GetMapping("/deleteProduct/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        this.productService.delete(id);

        return new ResponseEntity<>(this.productRepository.findById(id).toString(), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id")Long id, ModelAndView modelAndView){

        modelAndView.addObject("product",this.productService.findById(id));
        modelAndView.setViewName("details-product");
        return  modelAndView;
    }


    @GetMapping("/viewProducts")
    public ModelAndView viewProducts(ModelAndView modelAndView) {

        List<ProductServiceModel> productServiceModels = this.productService.findAllItems();

        modelAndView.addObject("products",productServiceModels);
        modelAndView.setViewName("products-view");


        return modelAndView;



    }

}


