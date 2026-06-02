package org.example._kimicommunitybe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "Post")
public class PostEntity {
    //엔티티의 기본 키.
    @Id
    //기본 키 값을 자동으로 생성하도록 JPA에 지시하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //엔티티 클래스의 필드가 데이터베이스에 어떤 이름과 형식으로 저장될지 지정하는 어노테이션
    @Column(name = "post_id")
    //래퍼 클래스(Long)는 null 이 가능하다.기본 자료형은 null 이 불가능하다.
    private Long id;

    //post 관점에서 user 는 many to one 관계임을 명시.
    //지연로딩: 프록시(가짜 객체)로 연결 확인 후 한번에 처리하기 위해.
    //(수정!) 지연로딩 공부하고 user_id 어떻게 받을지 결정.
    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "user_id") // DB에 생성될 FK 컬럼명 지정
    //    private UserEntity user_id;

    private String title; // 제목


    private String  content; //본문 내용
    @Column(name = "content_image")
    //NULL 값 허용. Column 명시 안 하면 기본적으로 null 값 허용된다.
    private String  contentImage; // 닉네임

    @Column(name = "created_at")
    private String createdAt; //본문 내용

    @Column(name = "view_count")
    private Integer viewCount; //본문 내용

    @Column(name = "comment_count")
    private Integer  commentCount; //본문 내용

    //기본 생성자 정의.
    public  PostEntity() {};


}
