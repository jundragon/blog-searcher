package org.jundragon.blogsearcher.common.error.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.common.error.ErrorCodeInterface;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorCodeInterface {

    OK(HttpStatus.OK.value(), "200", "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "400", "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "500", "서버 에러"),

    ;

    private final int httpStatusCode;
    private final String errorCode;
    private final String description;
}
