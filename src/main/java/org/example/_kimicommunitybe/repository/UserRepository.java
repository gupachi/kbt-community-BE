package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
//CRUD 로직 처리, DB 접근 처리
public interface  UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}