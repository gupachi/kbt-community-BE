package org.example._kimicommunitybe.repository.Impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example._kimicommunitybe.entity.Post;
import org.example._kimicommunitybe.entity.QPost;
import org.example._kimicommunitybe.repository.PostCustomRepository;

import java.util.List;

@RequiredArgsConstructor // 중요: 그래야 jpaQueryFactory를 Spring이 넣어줍니다.
public class PostRepositoryImpl implements PostCustomRepository { // 1. 여기는 반드시 'class'여야 합니다!

    private final JPAQueryFactory jpaQueryFactory;

    private final QPost post = QPost.post;

    //SQL 조회 문
    //(수정!!!!) 올바르게 데이터 가져올 수 있도록 수정.
    @Override
    public List<Post> getAllPost(Integer lastSeenId) {
        return jpaQueryFactory.selectFrom(post)
                .where(
                        ltPostId(lastSeenId) // 아래에 만든 동적 조건 메서드 호출
                )
                .orderBy(post.Id.desc())
                .limit(10) // 페이징 처리
                .fetch();
    }

    @Override
    public Post getPost(Long postId) {
        return jpaQueryFactory.selectFrom(post)
                .where(post.Id.eq(postId))
                .fetchOne();
    }

    // 3. ID가 null일 때를 대비한 동적 쿼리용 하위 메서드
    private BooleanExpression ltPostId(Integer lastSeenId) {
        if (lastSeenId == null) {
            return null;
        }
        return post.Id.lt(lastSeenId);
    }
}
