package org.example._kimicommunitybe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user") // 실제 DB 테이블 이름을 적어주세요. (예: user)
public class User {

        // 1. 기본 키(PK) 매핑: DB의 int UN (Unsigned) -> Java의 Long
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // 2. user_id 컬럼 추가 (varchar(36))
        @Column(name = "user_id",unique = true, length = 36)
        private String userId;

        // 3. 이메일 (varchar(320), 중복 불가)
        @Column(unique = true, length = 320)
        private String email;

        // 4. 비밀번호 (varchar(20))
        @Column(length = 20)
        private String password;

        // 5. 닉네임 (varchar(10), 중복 불가)
        @Column(unique = true, length = 10)
        private String nickname;

        // 6. 프로필 이미지 URL (varchar(512), 스네이크 케이스 매핑)
//        @Column(name = "profile_image", length = 512)
//        private String profileImage;

        // 7. 활성화 여부 (tinyint(1) -> Java의 Boolean 또는 Integer)
        // 0 또는 1을 참/거짓으로 직관적으로 쓰기 위해 Boolean 매핑을 권장합니다.
        @Column(columnDefinition = "TINYINT(1)")
        private Boolean activate;

        // 기본 생성자
        public User() {}

        @PrePersist
        public void generateUserId() {
                // userId가 비어있을 때만 작동하도록 안전장치 설정
                if (this.userId == null) {
                        // 1. UUID를 생성합니다. (원래 길이는 36자리이고 하이픈이 포함되어 있습니다)
                        String fullUuid = UUID.randomUUID().toString();

                        // 2. 하이픈(-)을 모두 제거하고, 앞에서부터 16자리만 똑 자릅니다.
                        this.userId = fullUuid.replace("-", "").substring(0, 16);
                }
        }
}
