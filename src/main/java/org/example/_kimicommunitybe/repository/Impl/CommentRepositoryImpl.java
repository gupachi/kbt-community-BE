package org.example._kimicommunitybe.repository.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example._kimicommunitybe.entity.Comment;
import org.example._kimicommunitybe.entity.QComment;
import org.example._kimicommunitybe.repository.CommentCustomRepository;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentCustomRepository {
    private  final JPAQueryFactory jpaQueryFactory;
    private final QComment comment = QComment.comment;
    @Override
    public List<Comment> getComment(){
        //sql 쿼리문
        return jpaQueryFactory.selectFrom(comment)
                .orderBy(comment.commentId.desc())
                .fetch();
    }
}
