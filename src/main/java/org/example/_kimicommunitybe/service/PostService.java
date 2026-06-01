package org.example._kimicommunitybe.service;

import org.example._kimicommunitybe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PostService {
    @Autowired
    PostRepository postRepository;

    public String  readPost(Long postId){
       return postRepository.readPost(postId);
    }

}
