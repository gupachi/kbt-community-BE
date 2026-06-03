package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.entity.PostEntity;
import org.example._kimicommunitybe.service.PostQuerydslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostQuerydslController {
    @Autowired
    PostQuerydslService postQuerydslService;

    @GetMapping
    public List<PostEntity> getPost(@RequestParam(name = "lastSeenId")Integer id){
        return  postQuerydslService.getPost(id);
    }
}
