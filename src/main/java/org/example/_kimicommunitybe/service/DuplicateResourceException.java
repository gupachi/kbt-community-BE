package org.example._kimicommunitybe.service;

//RuntimeException 을 상속받아 에러를 정의하는 경우: 주로 비즈니스 로직 상의 문제나 특정 조건 불만족 시 의도적으로 발생시켜 애플리케이션의 흐름을 제어
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException (String message){
        super(message);
    }
}
