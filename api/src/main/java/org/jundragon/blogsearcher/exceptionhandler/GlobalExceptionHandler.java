package org.jundragon.blogsearcher.exceptionhandler;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.jundragon.blogsearcher.error.impl.ErrorCode;
import org.jundragon.blogsearcher.response.ApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE) // 가장 낮은 순위로 적용
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> exception(Exception e) {
        String errorId = UUID.randomUUID().toString();
        log.error("[{}] Unknown Exception. message={}", errorId, e.getMessage(), e);

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ApiResponse.ERROR(ErrorCode.SERVER_ERROR)
            );
    }
}
