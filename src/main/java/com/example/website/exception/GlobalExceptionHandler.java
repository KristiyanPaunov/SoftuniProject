package com.example.website.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError500(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ModelAndView mav = new ModelAndView("exception");
        mav.addObject("error", "500");
        return mav;
    }

    @ExceptionHandler(WrongCategoryException.class)
    public ModelAndView handleWrongCategory(WrongCategoryException ex) {
        ModelAndView mav = new ModelAndView("Exception");
        mav.setViewName("Exception");
        mav.addObject("message", ex.getMessage());
        return mav;
    }

}
