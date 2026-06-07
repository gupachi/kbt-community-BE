package org.example._kimicommunitybe.common.error.errorCode;

import org.springframework.http.HttpStatus;

//errorCode json에 들어갈 요소 정리.
public interface ErrorCode {
    String name();
    HttpStatus getHttpStatus();
    String getMessage();
}


