package com.example.webappspringboot.services;

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

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Integer id){
        return postRepository.findById(id);
    }

    public Optional<Post> updatePostById(Integer id, Post post){
       post.setId(id); //if I receive body without id
       return postRepository.findById(id).map(postInDatabase ->Optional.of(postRepository.save(post))).orElseGet(()->Optional.empty());
       //is it OK?
    }


    //DOESNT WORK!!!!!!!
    public Optional<Post> deletePostById(Integer id){
        var postToDelete = postRepository.findById(id);
        postToDelete.ifPresent(post ->postRepository.deleteById(id));
        return postToDelete;
        //could it be written better?
    }
    //DOESNT WORK!!!!!!!
}
