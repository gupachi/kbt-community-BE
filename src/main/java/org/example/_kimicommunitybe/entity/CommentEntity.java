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
@Table(name="Comment")
public class CommentEntity {
    //엔티티의 기본 키.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    //(수정!!) postId 추가해야 됨.


    private String text;


    @Column(name = "created_at")
    private String createdAt; //본문 내용

    public  CommentEntity(){};

}
