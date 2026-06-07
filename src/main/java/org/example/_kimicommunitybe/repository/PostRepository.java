package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends  JpaRepository<Post,Long>,PostCustomRepository{


}
