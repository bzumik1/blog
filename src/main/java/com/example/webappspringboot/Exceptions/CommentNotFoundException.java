package com.example.webappspringboot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //if it is not found throws this exception
public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(){
        super("Resource not found!");
    }

    public CommentNotFoundException(String message){
        super(message,null);
    }

    public CommentNotFoundException(Integer id){
        super("No comment found with id = "+id,null);
    }

    public CommentNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
