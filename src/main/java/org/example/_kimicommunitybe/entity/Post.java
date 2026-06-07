package org.example._kimicommunitybe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "post") // 관례상 테이블명은 소문자를 많이 사용합니다.
public class Post {

    // 2. PK 설정 및 전략 수정 (SEQUENCE -> IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; // 대문자 'Id' -> 소문자 'id'로 수정, @Column(name="post_id") 삭제

    // 3. 누락되었던 post_no 컬럼 추가 (int UN -> Long)
    @Column(name = "post_no")
    private Long postNo;

    // 4. 객체지향적인 이름으로 변경 (userId -> user)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 5. TEXT 타입 명시
    @Column(columnDefinition = "TEXT")
    private String title;

    // 6. DB 컬럼명(context)과 자바 변수명(content) 매핑
    @Column(name = "context", columnDefinition = "TEXT")
    private String content;

    // 7. 이미지 컬럼명 불일치 수정 (content_image -> post_image)
    @Column(name = "post_image", length = 512)
    private String postImage;

    // 8. 날짜 타입 수정 (String -> LocalDateTime)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 9. int UN 매핑 (Integer -> Long)
    @Column(name = "view_count")
    private Integer viewCount;

    // 10. DB 스키마에 없는 컬럼 처리
    // 만약 DB에 컬럼을 추가할 예정이라면 @Column을 유지하시고,
    // DB엔 저장 안 하고 자바에서만 임시로 쓸 값이라면 @Transient를 붙여주세요.
    @Column(name = "comment_count")
    private Integer commentCount;

    // 기본 생성자
    public Post() {}

}