package com.example.webappspringboot.controllers;

import com.example.webappspringboot.Exceptions.ResourceNotFoundException;
import com.example.webappspringboot.models.Comment;
import com.example.webappspringboot.models.Post;
import com.example.webappspringboot.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    //check ResponseEntity vs @ResponseStatus
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return new ResponseEntity<>(postService.retrieveAllPosts(),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id){
        return postService.retrievePostById(id)
                .map(post -> new ResponseEntity<>(post,HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("No post found with id = "+id));
                //is it better than null in responseEntity?
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable Integer id,@RequestBody Post post){
        return postService.updatePostById(id,post)
                .map(newPost -> new ResponseEntity<>(newPost,HttpStatus.OK))
                .orElseThrow(()->new ResourceNotFoundException("No post found with id = "+id));
                //is it better than null in responseEntity?
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Integer id){
        return postService.deletePostById(id)
                .map(post -> new ResponseEntity<>(post,HttpStatus.OK))
                .orElseThrow(()->new ResourceNotFoundException("No post found with id = "+id));
                //is it better than null in responseEntity?

    }


}
