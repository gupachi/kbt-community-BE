package org.example._kimicommunitybe.controller;


import org.example._kimicommunitybe.dto.Request.CommentCreateRequestDTO;
import org.example._kimicommunitybe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping
    //접근제한자 [return 타입] 이름(파라미터)
    public String   loginUser(@RequestBody CommentCreateRequestDTO comment) {
        return commentService.createComment(comment);

    }
}
