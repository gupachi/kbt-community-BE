package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long>,CommentCustomRepository{


}
