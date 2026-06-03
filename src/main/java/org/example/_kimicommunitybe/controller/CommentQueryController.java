package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.entity.CommentEntity;
import org.example._kimicommunitybe.entity.PostEntity;
import org.example._kimicommunitybe.service.CommentQuerydslService;
import org.example._kimicommunitybe.service.PostQuerydslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentQueryController {
    @Autowired
    CommentQuerydslService commentQuerydslService;

    @GetMapping
    public List<CommentEntity> getComment(){
        return commentQuerydslService.getComment();
    }
}
