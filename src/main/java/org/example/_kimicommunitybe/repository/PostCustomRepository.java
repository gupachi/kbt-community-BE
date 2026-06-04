package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.entity.PostEntity;

import java.util.List;

public interface PostCustomRepository {
    List<PostEntity> getAllPost(Integer lastSeenId);
    PostEntity getPost(Long postId);
}
