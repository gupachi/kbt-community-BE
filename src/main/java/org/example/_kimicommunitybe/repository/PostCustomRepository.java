package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.entity.PostEntity;

import java.util.List;

public interface PostCustomRepository {
    List<PostEntity> getPost(Integer lastSeenId);
}
