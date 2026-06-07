package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//DB에 저장할 RefreshToken 생성.
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    // 주어진 토큰이면서 아직 무효화되지 않은 레코드를 Optional로 조회
    Optional<RefreshToken> findByTokenAndRevokedFalse(String token);
    //리프레시 토큰 일괄 삭제
    void deleteByUserId(String userId);
}
