package org.example._kimicommunitybe.common.error.errorCode;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final RuntimeException code;
    private final String message;
}

