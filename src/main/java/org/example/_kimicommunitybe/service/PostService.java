package org.example._kimicommunitybe.service;

import org.example._kimicommunitybe.dto.Request.PostCreateRequestDTO;
import org.example._kimicommunitybe.entity.PostEntity;
import org.example._kimicommunitybe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public String  createPost(PostCreateRequestDTO post){
        PostEntity postEntity = new PostEntity();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = now.format(formatter);

        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setContentImage(post.getContent_image());
        postEntity.setCreatedAt(formattedDate);
        postEntity.setCommentCount(0);
        postEntity.setViewCount(0);

        PostEntity savedEntiity = postRepository.save(postEntity);

        return  "";
    }

}
