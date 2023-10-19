package org.jundragon.blogsearcher.api;

import org.jundragon.blogsearcher.error.ErrorCodeInterface;

public record ApiResponse<T>(Result result, T body) {

    public static <T> ApiResponse<T> OK(T data) {
        return new ApiResponse<>(Result.OK(), data);
    }

    public static ApiResponse<Object> ERROR(ErrorCodeInterface errorCode) {
        return new ApiResponse<>(Result.ERROR(errorCode), null);
    }

    public static ApiResponse<Object> ERROR(ErrorCodeInterface errorCode, String description) {
        return new ApiResponse<>(Result.ERROR(errorCode, description), null);
    }
}
