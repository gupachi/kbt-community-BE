package org.example._kimicommunitybe.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example._kimicommunitybe.entity.PostEntity;
import org.example._kimicommunitybe.entity.QPostEntity;
import org.example._kimicommunitybe.repository.PostCustomRepository;
import org.example._kimicommunitybe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostQuerydslService {

    @Autowired
    // PostCustomRepository 대신 통합된 PostRepository를 주입받는 것이 좋습니다.
    PostRepository postRepository;

    public List<PostEntity> getPost(Integer lastSeenId) {
        // 그렇게 하면 jpa 기본 메서드와 querydsl 메서드를 한 곳에서 모두 쓸 수 있습니다.
        return postRepository.getPost(lastSeenId);
    }
}
