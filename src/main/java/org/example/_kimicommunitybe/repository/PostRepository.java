package org.example._kimicommunitybe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example._kimicommunitybe.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends  JpaRepository<PostEntity,Long>,PostCustomRepository{


}
