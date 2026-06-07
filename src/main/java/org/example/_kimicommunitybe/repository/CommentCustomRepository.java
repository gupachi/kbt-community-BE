package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.entity.Comment;

import java.util.List;

public interface CommentCustomRepository {
    //(수정!!!) postId 를 외래키로 받아와야 됨.
    List<Comment> getComment();

}
