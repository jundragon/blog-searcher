package org.jundragon.blogsearcher.exceptionhandler;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.jundragon.blogsearcher.dto.ApiResponse;
import org.jundragon.blogsearcher.error.ErrorCodeInterface;
import org.jundragon.blogsearcher.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j(topic = "error-log")
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse<Object>> exception(ApiException e) {

        String errorId = UUID.randomUUID().toString();
        log.error("[{}] API Exception. message={}", errorId, e.getMessage(), e);

        ErrorCodeInterface errorCode = e.getErrorCode();

        return ResponseEntity
            .status(errorCode.getHttpStatusCode())
            .body(
                ApiResponse.ERROR(errorCode, errorCode.getDescription())
            );
    }
}
