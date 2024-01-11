package org.jundragon.blogsearcher.error;

public interface ErrorCodeInterface {

    int getHttpStatusCode();

    String getErrorCode();

    String getDescription();
}
