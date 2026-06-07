package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.entity.Post;

import java.util.List;

public interface PostCustomRepository {
    List<Post> getAllPost(Integer lastSeenId);
    Post getPost(Long postId);
}
