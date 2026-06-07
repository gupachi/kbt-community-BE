package org.example._kimicommunitybe.controller;


import jakarta.validation.Valid;
import org.example._kimicommunitybe.dto.Request.CommentRequestDTO;
import org.example._kimicommunitybe.entity.Comment;
import org.example._kimicommunitybe.service.CommentQuerydslService;
import org.example._kimicommunitybe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    CommentQuerydslService commentQuerydslService;

    //댓글 조회
    @GetMapping
    public List<Comment> getComment(){
        return commentQuerydslService.getComment();
    }

    //댓글 생성
    @PostMapping
    //접근제한자 [return 타입] 이름(파라미터)
    public String  createComment(@RequestBody @Valid CommentRequestDTO comment) {
        return commentService.createComment(comment);
    }

    //댓글 수정
    @PatchMapping
    public String updateComment(
            @RequestParam(name = "commentId") Long commentId,
            @RequestBody @Valid CommentRequestDTO comment){
        return commentService.updateComment(commentId,comment);
    }

    //댓글 삭제
    @DeleteMapping
    public String deleteComment(
        @RequestParam(name = "commentId") Long id){
        return  commentService.deleteComment(id);
    }
}
