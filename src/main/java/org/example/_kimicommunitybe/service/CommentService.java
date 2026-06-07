package org.example._kimicommunitybe.service;

import jakarta.transaction.Transactional;
import org.example._kimicommunitybe.dto.Request.CommentRequestDTO;
import org.example._kimicommunitybe.entity.Comment;
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
    public String createComment(CommentRequestDTO comment){
        Comment commentEntity = new Comment();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = now.format(formatter);

        commentEntity.setText(comment.getText());
        commentEntity.setCreatedAt(formattedDate);


        Comment savedEntiity = commentRepository.save(commentEntity);
        return  "";
    }
    //게시글 수정.
    @Transactional
    public String updateComment( Long commentId, CommentRequestDTO comment){
        commentRepository.findById(commentId).ifPresent(c -> {
            if (comment.getText() != null) c.setText(comment.getText());
        });
        return "댓글이 수정되었습니다.";
    }

    public String deleteComment( Long commentId){
        commentRepository.deleteById(commentId);
        return "댓글이 삭제되었습니다.";
    }

}
