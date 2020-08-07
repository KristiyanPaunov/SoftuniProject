package com.example.website.web;

import com.example.website.exception.WrongCategoryException;
import com.example.website.model.binding.ProductAddBindingModel;
import com.example.website.model.repository.CartRepository;
import com.example.website.model.repository.ProductRepository;
import com.example.website.model.service.CategoryServiceModel;
import com.example.website.model.service.ProductServiceModel;
import com.example.website.service.CategoryService;
import com.example.website.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    private CartRepository cartRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/addProduct")
    public String getProduct(Model model) {
        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }

        List<CategoryServiceModel> categoryServiceModels = this.categoryService.findAllCategories();
        System.out.println("CATEGORIES: " + categoryServiceModels.size());
        model.addAttribute("categoryServiceModels",categoryServiceModels);

        return "add-product";

    }


    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) throws WrongCategoryException {


        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productAddBindingModel",productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel",bindingResult);

            return "redirect:addProduct";
        }


        this.productService
                .addItem(this.modelMapper
                        .map(productAddBindingModel, ProductServiceModel.class));

        return "redirect:viewProducts";

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
    public ModelAndView viewProducts(ModelAndView modelAndView, HttpSession httpSession) {

        List<ProductServiceModel> productServiceModels = this.productService.findAllItems();
        modelAndView.addObject("products",productServiceModels);
        modelAndView.setViewName("products-view");


        return modelAndView;



    }

}


