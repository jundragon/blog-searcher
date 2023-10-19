package org.jundragon.blogsearcher.exception;

import lombok.Getter;
import org.jundragon.blogsearcher.error.ErrorCodeInterface;

@Getter
public class ApiException extends RuntimeException {

    private final ErrorCodeInterface errorCode;
    private final String errorDescription;

    public ApiException(ErrorCodeInterface errorCode) {
        this(errorCode, errorCode.getDescription());
    }

    public ApiException(ErrorCodeInterface errorCode, String errorDescription) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public ApiException(ErrorCodeInterface errorCode, Throwable tx) {
        super(tx);
        this.errorCode = errorCode;
        this.errorDescription = errorCode.getDescription();
    }

    public ApiException(ErrorCodeInterface errorCode, Throwable tx, String errorDescription) {
        super(tx);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
