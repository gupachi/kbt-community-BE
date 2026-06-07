package org.example._kimicommunitybe.common.error.handler;

import org.example._kimicommunitybe.common.error.errorCode.ErrorResponse;
import org.example._kimicommunitybe.common.error.exception.RestApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    private ResponseEntity<ErrorResponse> handleCException(RestApiException e) {
        logger.warn("Exception : ", e);

        return ResponseEntity.internalServerError().body(new ErrorResponse(e,e.getMessage()));
    }
}