package org.example._kimicommunitybe.service;

import jakarta.transaction.Transactional;
import org.example._kimicommunitybe.dto.Request.PostRequestDTO;
import org.example._kimicommunitybe.entity.Post;
import org.example._kimicommunitybe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    //게시글 생성.
    public String  createPost(PostRequestDTO post){
        Post postEntity = new Post();

        LocalDateTime now = LocalDateTime.now();


        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setPostImage(post.getContent_image());
        postEntity.setCreatedAt(now);
        postEntity.setCommentCount(0);
        postEntity.setViewCount(0);

        Post savedEntiity = postRepository.save(postEntity);



        return  "";
    }
    //게시글 수정.
    @Transactional
    public String updatePost(Long id,PostRequestDTO post){
        String title = post.getTitle();
        String content = post.getContent();
        String content_image = post.getContent_image();
        postRepository.findById(id).ifPresent(p -> {
            if (title != null) p.setTitle(title);
            if (content != null) p.setContent(content);
            if (content_image != null) p.setPostImage(content_image);
        });
        return "";
    }
    //게시글 삭제.
    @Transactional
    public String deletePost( Long postId){
        postRepository.deleteById(postId);
        return "게시글이 삭제되었습니다.";
    }



}
