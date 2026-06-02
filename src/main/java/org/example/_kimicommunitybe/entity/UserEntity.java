package org.example._kimicommunitybe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;


@Entity
//getId(),getEmail() 을 정의하지 않고도 사용 가능하다.
@Getter
@Setter
@Table(name = "User") // 데이터베이스 테이블 이름 지정
public class UserEntity {

        //엔티티의 기본 키.
        @Id
        //기본 키 값을 자동으로 생성하도록 JPA에 지시하는 어노테이션
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        //엔티티 클래스의 필드가 데이터베이스에 어떤 이름과 형식으로 저장될지 지정하는 어노테이션
        @Column(name = "user_id")
        //래퍼 클래스(Long)는 null 이 가능하다.기본 자료형은 null 이 불가능하다.
        private Long id;

        @Column(unique = true)
        private String email; // 이메일 (중복 불가)


        private String password; // 암호화된 비밀번호가 저장될 공간

        @Column(unique = true)
        private String nickname; // 닉네임

        @Column(name = "profile_image")
        private String profileImage; // 프로필 이미지 URL (스네이크 케이스 매핑)

         @Column
         private Character activate; // 프로필 이미지 URL (스네이크 케이스 매핑)

        //기본 생성자 정의.
        public  UserEntity() {};

}

