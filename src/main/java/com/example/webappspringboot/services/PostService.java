package com.example.webappspringboot.services;

import com.example.webappspringboot.models.Post;
import com.example.webappspringboot.repositories.PostRepository;
import org.hibernate.Hibernate;
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

    public Optional<Post> updatePostById(Integer id, Post post){
       post.setId(id); //if I receive body without id
       return postRepository.findById(id).map(postInDatabase ->Optional.of(postRepository.save(post))).orElseGet(Optional::empty);
       //is it OK?
    }

    public Optional<Post> deletePostById(Integer id) {
        var postToDelete = postRepository.findById(id); // must to be fetch eager or Hibernate.initialize
        postToDelete.ifPresent(post -> {
            Hibernate.initialize(post.getComments());// PROBLEM BECAUSE IT CALLS QUERY TWO TIMES INSTEAD OF ONLY ONCE
            postRepository.deleteById(id);
        }); // Hibernate.initialize is used because of lazy fetch
        return postToDelete; //MAYBE I DON'T WANT TO RETURN ANYTHING
        //could it be written better?
    }
}
