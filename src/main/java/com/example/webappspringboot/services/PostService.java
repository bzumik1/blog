package com.example.webappspringboot.services;

import com.example.webappspringboot.Exceptions.PostNotFoundException;
import com.example.webappspringboot.models.Post;
import com.example.webappspringboot.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public List<Post> retrieveAllPosts(){
        return postRepository.findAll();
    }

    public Optional<Post> retrievePostById(Integer id){
        return postRepository.findById(id);
    }

    public boolean updatePostById(Integer id, Post post){
       post.setId(id); //if I receive body without id
       if(checkById(id)){
           postRepository.save(post);
       }
       return true;
    }

    public boolean deletePostById(Integer id) {
        if(checkById(id)){ //check if it is in DB or throws an error
            postRepository.deleteById(id);
        }
        return true;
    }












    public boolean checkById(Integer id){
        var postInDatabase = postRepository.findById(id);
        if(postInDatabase.isEmpty()){
            throw new PostNotFoundException(id);
        }
        else{
            return true;
        }
    }



}
