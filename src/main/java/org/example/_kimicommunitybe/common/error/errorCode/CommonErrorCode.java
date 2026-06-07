package org.example._kimicommunitybe.common.error.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
//400,401,403,405,500 와 같은 보편적인 에러를 처리한다.
public  enum CommonErrorCode implements ErrorCode {
    // 400: 잘못된 요청
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),

    // 401: 인증 자격 증명이 없음 (미로그인 등)
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized access"),

    // 403: 권한이 없음 (로그인은 했으나 접근 불가)
    FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden access"),

    // 404: 리소스를 찾을 수 없음 (기존 코드 유지)
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),

    // 405: 지원하지 않는 HTTP Method (GET, POST 등) 호출
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Method not allowed"),

    // 500: 서버 내부 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}