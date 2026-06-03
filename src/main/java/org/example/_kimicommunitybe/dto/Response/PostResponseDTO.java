package org.example._kimicommunitybe.dto.Response;

import jakarta.persistence.Column;

public class PostResponseDTO {

    private Long id;

    private String title; // 제목


    private String  content; //본문 내용

    //NULL 값 허용. Column 명시 안 하면 기본적으로 null 값 허용된다.
    private String  contentImage; // 닉네임


    private String createdAt; //본문 내용


    private Integer viewCount; //본문 내용


    private Integer  commentCount;
}
