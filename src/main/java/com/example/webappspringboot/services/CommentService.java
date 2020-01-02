package com.example.webappspringboot.services;

import com.example.webappspringboot.Exceptions.CommentNotFoundException;
import com.example.webappspringboot.Exceptions.PostNotFoundException;
import com.example.webappspringboot.models.Comment;
import com.example.webappspringboot.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService; // should it be here?

    @Autowired
    public CommentService(CommentRepository commentRepository, PostService postService){
        this.commentRepository=commentRepository;
        this.postService = postService;
    }


    public Comment createComment(Integer postID, Comment comment){
        var postInDatabase = postService.retrievePostById(postID) //returns post or throws an error
                .orElseThrow(()->new PostNotFoundException("No post found with id = "+postID));
        var commentInDatabase = commentRepository.save(comment); // if post of postID is in DB stores comment in DB
        postInDatabase.getComments().add(commentInDatabase);
        postService.updatePostById(postID,postInDatabase);
        return commentInDatabase;
    }

    public boolean deleteCommentById(Integer postID, Integer commentID){
        if(postService.checkById(postID)){
            if (checkById(commentID)) {
                commentRepository.deleteById(commentID);
            }
        }
        return true;
    }





    public boolean checkById(Integer id){
        var postInDatabase = commentRepository.findById(id);
        if(postInDatabase.isEmpty()){

            throw new CommentNotFoundException(id);
        }
        else{
            return true;
        }
    }



}
