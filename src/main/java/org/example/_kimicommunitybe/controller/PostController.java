package org.example._kimicommunitybe.controller;

import jakarta.validation.Valid;
import org.example._kimicommunitybe.dto.Request.PostRequestDTO;
import org.example._kimicommunitybe.entity.Post;
import org.example._kimicommunitybe.service.PostLikeService;
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
    PostLikeService postLikeService;

    @Autowired
    PostQuerydslService postQuerydslService;

    //게시글 목록 조회.
    @GetMapping("/all")
    public List<Post> getAllPost(@RequestParam(name = "lastSeenId")Integer id){
        return  postQuerydslService.getAllPost(id);
    }
    //게시글 조회.
    @GetMapping
    public Post getPost(@RequestParam(name = "postId")Long id){
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

    // 좋아요 등록 (예: PATCH /api/posts/1/like?userId=5)
    @PatchMapping("/{postId}/like")
    public String addPostLike(
            @PathVariable("postId") Long postId,
            @RequestParam("userId") Long userId) { // 어떤 유저가 눌렀는지 받아야 함

        postLikeService.addLike(postId, userId);
        return "좋아요 등록 완료";
    }

    // 좋아요 취소 (예: PATCH /api/posts/1/unlike?userId=5)
    @PatchMapping("/{postId}/unlike")
    public String removePostLike(
            @PathVariable("postId") Long postId,
            @RequestParam("userId") Long userId) {

        postLikeService.removeLike(postId, userId);
        return "좋아요 취소 완료";
    }

}
