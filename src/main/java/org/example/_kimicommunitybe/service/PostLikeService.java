package org.example._kimicommunitybe.service;

import org.example._kimicommunitybe.entity.PostLike;
import org.example._kimicommunitybe.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {
    @Autowired
    PostLikeRepository postLikeRepository;
    
    // 좋아요 등록
    public void addLike(Long postId, Long userId) {
        PostLike post = postLikeRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        PostLike like = new PostLike();
        like.setId(post.getId());
        like.setUserId(post.getUserId());
        // 리스트에 추가하는 대신 DB에 행을 저장
    }

    // 좋아요 취소
    public void removeLike(Long postId, Long userId) {
        // post_id와 user_id가 일치하는 좋아요 데이터를 찾아서 삭제
        PostLike like =  PostLikeRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("좋아요 기록이 없습니다."));

    }
}
