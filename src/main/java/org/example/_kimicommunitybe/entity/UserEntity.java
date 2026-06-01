package org.example._kimicommunitybe.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;


@Entity
@Getter// JPA를 위한 기본 생성자 (접근 제어)
@Setter
@Table(name = "users") // 데이터베이스 테이블 이름 지정
public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // 회원 식별키 (자동 증가)

        @Column(nullable = false, unique = true)
        private String email; // 이메일 (중복 불가)

        @Column(nullable = false)
        private String password; // 암호화된 비밀번호가 저장될 공간

        @Column(nullable = false, length = 10)
        private String nickname; // 닉네임

        @Column(name = "profile_image", nullable = false)
        private String profileImage; // 프로필 이미지 URL (스네이크 케이스 매핑)

        // 안전한 객체 생성을 위한 빌더 패턴 적용
        public UserEntity(String email, String password, String nickname, String profileImage) {
            this.email = email;
            this.password = password;
            this.nickname = nickname;
            this.profileImage = profileImage;
        }


}

