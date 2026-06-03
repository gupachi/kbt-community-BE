package org.example._kimicommunitybe.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.example._kimicommunitybe.dto.Request.PostRequestDTO;
import org.example._kimicommunitybe.dto.Request.UserPasswordRequestDTO;
import org.example._kimicommunitybe.dto.Response.PostResponseDTO;
import org.example._kimicommunitybe.entity.PostEntity;
import org.example._kimicommunitybe.entity.QPostEntity;
import org.example._kimicommunitybe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    //게시글 생성.
    public String  createPost(PostRequestDTO post){
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
    //게시글 수정.
    @Transactional
    public String updatePost(Long id,PostRequestDTO post){
        String title = post.getTitle();
        String content = post.getContent();
        String content_image = post.getContent_image();
        postRepository.findById(id).ifPresent(p -> {
            if (title != null) p.setTitle(title);
            if (content != null) p.setContent(content);
            if (content_image != null) p.setContentImage(content_image);
        });
        return "";
    }
    //게시글 삭제.
    public String deletePost( Long postId){
        postRepository.deleteById(postId);
        return "게시글이 삭제되었습니다.";
    }



}
