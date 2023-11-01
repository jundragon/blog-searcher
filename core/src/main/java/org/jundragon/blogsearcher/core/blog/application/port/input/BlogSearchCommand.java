package org.jundragon.blogsearcher.core.blog.application.port.input;

import java.util.Objects;
import lombok.Builder;
import org.jundragon.blogsearcher.core.blog.domain.BlogSearchSortType;


public record BlogSearchCommand(String keyword, BlogSearchSortType sort, Integer page, Integer size) {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;

    @Builder
    public BlogSearchCommand(String keyword, BlogSearchSortType sort, Integer page, Integer size) {
        this.keyword = keyword;
        this.sort = sort;
        this.page = Objects.isNull(page) ? DEFAULT_PAGE : page;
        this.size = Objects.isNull(size) ? DEFAULT_SIZE : size;
    }
}
