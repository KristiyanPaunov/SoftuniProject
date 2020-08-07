package com.example.website.exception;

import org.springframework.http.HttpStatus;

public class WrongCategoryException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public WrongCategoryException(String message){
        super(message);
    }

}
