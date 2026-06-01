package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.dto.UserUpdateReqDTO;
import org.example._kimicommunitybe.service.LoginService;
import org.example._kimicommunitybe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;
    //게시글 상세 조회.
    @GetMapping("/{postId}")
    public String getPost(@PathVariable("postId") Long Id){
        return postService.readPost(Id);
    }
    //게시글 수정.



}
