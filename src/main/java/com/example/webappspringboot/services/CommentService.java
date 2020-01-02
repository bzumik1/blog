package com.example.webappspringboot.services;

import com.example.webappspringboot.Exceptions.ResourceNotFoundException;
import com.example.webappspringboot.models.Comment;
import com.example.webappspringboot.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService; // should it be here?

    @Autowired
    public CommentService(CommentRepository commentRepository, PostService postService){
        this.commentRepository=commentRepository;
        this.postService = postService;
    }


    public Comment createNewComment(Integer postID, Comment comment){
        var postInDatabase = postService.retrievePostById(postID) //returns post or throws an error
                .orElseThrow(()->new ResourceNotFoundException("No post found with id = "+postID));
        var commentInDatabase = commentRepository.save(comment); // if post of postID is in DB stores comment in DB
        postInDatabase.getComments().add(commentInDatabase);
        postService.updatePostById(postID,postInDatabase);
        return commentInDatabase;
    }



}
