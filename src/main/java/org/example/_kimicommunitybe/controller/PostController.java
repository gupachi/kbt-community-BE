package org.example._kimicommunitybe.controller;

import jakarta.validation.Valid;
import org.example._kimicommunitybe.dto.Request.PostRequestDTO;
import org.example._kimicommunitybe.entity.PostEntity;
import org.example._kimicommunitybe.service.PostQuerydslService;
import org.example._kimicommunitybe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    PostQuerydslService postQuerydslService;

    //게시글 목록 조회.
    @GetMapping("/all")
    public List<PostEntity> getAllPost(@RequestParam(name = "lastSeenId")Integer id){
        return  postQuerydslService.getAllPost(id);
    }
    //게시글 조회.
    @GetMapping
    public PostEntity getPost(@RequestParam(name = "postId")Long id){
        return  postQuerydslService.getPost(id);
    }
    //게시글 등록
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody @Valid PostRequestDTO post){
        String result= postService.createPost(post);
        //(수정!) : status code 랑,message 어떻게 할지 결정.
        return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 성공적으로 만들어졌습니다.");
    }
    //게시글 수정
    @PatchMapping
    public String updatePost(@RequestParam("postId") Long postId,@RequestBody @Valid PostRequestDTO post){
        return postService.updatePost(postId,post);
    }
    //게시글 삭제
    @DeleteMapping
    public String deletePost(
            @RequestParam(name = "postId") Long id){
        return  postService.deletePost(id);
    }

}
