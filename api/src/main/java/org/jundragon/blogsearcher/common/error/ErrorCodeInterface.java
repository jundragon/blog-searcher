package org.jundragon.blogsearcher.common.error;

public interface ErrorCodeInterface {

    int getHttpStatusCode();

    String getErrorCode();

    String getDescription();
}
