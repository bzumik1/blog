package com.example.webappspringboot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //if it is not found throws this exception
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super("Resource not found!");
    }

    public ResourceNotFoundException(String message){
        super(message,null);
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
