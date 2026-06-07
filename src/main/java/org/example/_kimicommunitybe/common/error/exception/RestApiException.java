package org.example._kimicommunitybe.common.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example._kimicommunitybe.common.error.errorCode.ErrorCode;

//발생한 예외를 처리할 예외 클래스 정의.
@Getter
@RequiredArgsConstructor

public class RestApiException extends RuntimeException {
    private final ErrorCode errorCode;
}
