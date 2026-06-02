package org.example._kimicommunitybe.service;

import org.example._kimicommunitybe.dto.Request.CommentCreateRequestDTO;
import org.example._kimicommunitybe.entity.CommentEntity;
import org.example._kimicommunitybe.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    //게시글 추가.
    public String createComment(CommentCreateRequestDTO comment){
        CommentEntity commentEntity = new CommentEntity();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = now.format(formatter);

        commentEntity.setText(comment.getText());
        commentEntity.setCreatedAt(formattedDate);


        CommentEntity savedEntiity = commentRepository.save(commentEntity);
        return  "";
    }
    //게시글 수정.


}
