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
@Table(name="comment")
public class Comment {
    //댓글 Id(기본키)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    //게시글 Id(외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post postId;

    private String text;


    @Column(name = "created_at")
    private String createdAt; //본문 내용

    public Comment(){};

}
