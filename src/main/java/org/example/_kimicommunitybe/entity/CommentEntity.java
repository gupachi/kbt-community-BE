package org.example._kimicommunitybe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name="Comment")
public class CommentEntity {
    //엔티티의 기본 키.
    @Id
    //기본 키 값을 자동으로 생성하도록 JPA에 지시하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //엔티티 클래스의 필드가 데이터베이스에 어떤 이름과 형식으로 저장될지 지정하는 어노테이션
    @Column(name = "comment_id")
    //래퍼 클래스(Long)는 null 이 가능하다.기본 자료형은 null 이 불가능하다.
    private Long id;

    //user_id FK 처리.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id") // DB에 생성될 FK 컬럼명 지정
    private PostEntity post_id;


    private String text;

    @Column(name = "user_ids")
    //user_id 들을 받고자 한다.
    private List<Long> userIds;

    @Column(name = "created_at")
    private String createdAt; //본문 내용

    public  CommentEntity(){};

}
