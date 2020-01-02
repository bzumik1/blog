package com.example.webappspringboot.controllers;

import com.example.webappspringboot.models.Comment;
import com.example.webappspringboot.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="api/posts/{postID}/comments")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable Integer postID, @RequestBody Comment comment){
        return new ResponseEntity<>(commentService.createNewComment(postID, comment), HttpStatus.CREATED);
    }
}
