package org.example._kimicommunitybe.service;

import jakarta.transaction.Transactional;
import org.example._kimicommunitybe.entity.Comment;
import org.example._kimicommunitybe.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentQuerydslService {

    @Autowired
    // PostCustomRepository 대신 통합된 PostRepository를 주입받는 것이 좋습니다.
    CommentRepository commentRepository;

    public List<Comment> getComment() {
        return commentRepository.getComment();
    }
}