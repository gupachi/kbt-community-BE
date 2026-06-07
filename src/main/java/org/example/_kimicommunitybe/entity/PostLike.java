package org.example._kimicommunitybe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post_like") // DB 테이블명
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. 외래키 매핑 (PostLikeEntity -> PostEntity로 수정)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_no", nullable = false) // DB 컬럼명인 post_no로 매핑
    private Post post; // 변수명도 postId가 아닌 객체 자체를 의미하는 post로 변경 권장

    // 3. user_id 타입 수정 (Long -> String)
    @Column(name = "user_id", length = 36, nullable = false)
    private String userId;

    // 기본 생성자
    public PostLike() {}

}