package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.dto.Request.PostCreateRequestDTO;
import org.example._kimicommunitybe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    //게시글 등록
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody  PostCreateRequestDTO post){
        String result= postService.createPost(post);
        //(수정!) : status code 랑,message 어떻게 할지 결정.
        return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 성공적으로 만들어졌습니다.");
    }
    //게시글 수정

}
