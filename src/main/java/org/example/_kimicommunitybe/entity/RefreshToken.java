package org.example._kimicommunitybe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "jwtToken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id", // 현재 토큰 테이블에 생성될 외래키 컬럼 이름
            referencedColumnName = "user_id", //참조할 User 테이블의 컬럼 이름
            columnDefinition = "VARCHAR(36)"
    )
    private User user;


    @Column(unique = true)
    private  String token;

    //Instant: 시간의 한 지점
    private  Instant expiresAt;

    private  boolean revoked;

}
