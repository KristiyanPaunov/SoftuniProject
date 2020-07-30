package com.example.website.web;

import com.example.website.model.binding.CategoryAddBindingModel;
import com.example.website.model.binding.ProductAddBindingModel;
import com.example.website.model.service.CategoryServiceModel;
import com.example.website.model.service.ProductServiceModel;
import com.example.website.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity addCategory(@RequestBody CategoryAddBindingModel categoryAddBindingModel) {

        this.categoryService
                .addCategory(this.modelMapper
                        .map(categoryAddBindingModel, CategoryServiceModel.class));


        return new ResponseEntity<>(categoryAddBindingModel, HttpStatus.OK);

    }

    @GetMapping("/deleteCategory/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        this.categoryService.delete(id);
        return new ResponseEntity<>("DELETE Response for category", HttpStatus.OK);
    }

    @GetMapping("/viewCategories")
    public ResponseEntity viewCategories() {

        List<CategoryServiceModel> categoryServiceModels = this.categoryService.findAllCategories();

        return new ResponseEntity<>(categoryServiceModels, HttpStatus.OK);

    }




}
