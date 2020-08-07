package com.example.website.web;

import com.example.website.exception.WrongCategoryException;
import com.example.website.model.binding.CategoryAddBindingModel;
import com.example.website.model.service.CategoryServiceModel;
import com.example.website.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/addCategory")
    public String adminRegister(Model model) {
        if (!model.containsAttribute("categoryAddBindingModel")) {
            model.addAttribute("categoryAddBindingModel", new CategoryAddBindingModel());
        }
        return "add-category";

    }

    @PostMapping(value = "/addCategory")
    public String addCategory(@Valid @ModelAttribute("categoryAddBindingModel") CategoryAddBindingModel categoryAddBindingModel,
                              BindingResult bindingResult,ModelAndView modelAndView)  {

        if(bindingResult.hasErrors()){
            throw new WrongCategoryException("There was a mistake while creating a category!");
        }



        this.categoryService
                .addCategory(this.modelMapper
                        .map(categoryAddBindingModel, CategoryServiceModel.class));


        return "redirect:viewCategories";

    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        this.categoryService.delete(id);
        return "categories";
    }

    @GetMapping("/viewCategories")
    public ModelAndView viewCategories(ModelAndView modelAndView) {

        List<CategoryServiceModel> categoryServiceModels = this.categoryService.findAllCategories();

        System.out.println(categoryServiceModels.size());

        modelAndView.addObject("categories",categoryServiceModels);
        modelAndView.setViewName("categories");



        return modelAndView;

    }




}
