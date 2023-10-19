package org.jundragon.blogsearcher.blogsource.request;

import java.util.Objects;
import lombok.Builder;
import org.jundragon.blogsearcher.core.blog.domain.BlogSearchSortType;

public record BlogSearchRequest(String query, BlogSearchSortType sort, Integer page, Integer size) {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;

    @Builder
    public BlogSearchRequest(String query, BlogSearchSortType sort, Integer page, Integer size) {
        this.query = query;
        this.sort = Objects.isNull(sort) ? BlogSearchSortType.ACCURACY : sort;
        this.page = Objects.isNull(page) ? DEFAULT_PAGE : page;
        this.size = Objects.isNull(size) ? DEFAULT_SIZE : size;
    }
}
