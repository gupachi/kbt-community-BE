package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  PostLikeRepository extends JpaRepository<PostLike,Long> {
    static Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId) {
        return null;
    }

}
