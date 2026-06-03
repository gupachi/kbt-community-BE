package org.example._kimicommunitybe.controller;


import org.example._kimicommunitybe.dto.Request.CommentRequestDTO;
import org.example._kimicommunitybe.entity.PostEntity;
import org.example._kimicommunitybe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    //댓글 생성.
    @PostMapping
    //접근제한자 [return 타입] 이름(파라미터)
    public String  createComment(@RequestBody CommentRequestDTO comment) {
        return commentService.createComment(comment);
    }
    //댓글 수정.
    @PatchMapping
    public String updateComment(
            @RequestParam(name = "commentId") Long commentId,
            @RequestBody CommentRequestDTO comment){
        return commentService.updateComment(commentId,comment);
    }
    //댓글 삭제.
    @DeleteMapping
    public String deleteComment(
        @RequestParam(name = "commentId") Long id){
        return  commentService.deleteComment(id);
    }
}
