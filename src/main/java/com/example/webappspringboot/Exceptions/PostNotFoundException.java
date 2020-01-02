package com.example.webappspringboot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //if it is not found throws this exception
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(){
        super("Resource not found!");
    }

    public PostNotFoundException(String message){
        super(message,null);
    }

    public PostNotFoundException(Integer id){
        super("No post found with id = "+id,null);
    }

    public PostNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
