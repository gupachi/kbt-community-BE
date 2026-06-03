package org.example._kimicommunitybe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example._kimicommunitybe.entity.CommentEntity;
import org.example._kimicommunitybe.entity.QCommentEntity;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentCustomRepository {
    private  final JPAQueryFactory jpaQueryFactory;
    private final QCommentEntity comment = QCommentEntity.commentEntity;
    @Override
    public List<CommentEntity> getComment(){
        //sql 쿼리문
        return jpaQueryFactory.selectFrom(comment)
                .orderBy(comment.commentId.desc())
                .fetch();
    }
}
