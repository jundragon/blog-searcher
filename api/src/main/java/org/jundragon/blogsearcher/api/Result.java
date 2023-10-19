package org.jundragon.blogsearcher.api;

import lombok.Builder;
import org.jundragon.blogsearcher.error.ErrorCodeInterface;
import org.jundragon.blogsearcher.error.impl.ErrorCode;

@Builder
public record Result(String code, String message, String description) {

    public static Result OK() {
        return Result.builder()
            .code(ErrorCode.OK.getErrorCode())
            .message(ErrorCode.OK.getDescription())
            .description("성공")
            .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCode) {
        return Result.builder()
            .code(errorCode.getErrorCode())
            .message(errorCode.getDescription())
            .description("에러 발생")
            .build();
    }

    public static Result ERROR(ErrorCodeInterface errorCode, String description) {
        return Result.builder()
            .code(errorCode.getErrorCode())
            .message(errorCode.getDescription())
            .description(description)
            .build();
    }
}
