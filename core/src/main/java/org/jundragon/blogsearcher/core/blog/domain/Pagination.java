package org.jundragon.blogsearcher.core.blog.domain;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Pagination {

    private static final int END_PAGE = -1;

    private final boolean hasNextPage;
    private final Integer nextPage;

    private final Integer currentPage;
    private final Integer totalCount;
    private final Integer size;

    @Builder
    public Pagination(Integer currentPage, Integer totalCount, Integer size) {

        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.size = size;

        if (Objects.isNull(currentPage) || Objects.isNull(totalCount)) {
            this.hasNextPage = false;
            this.nextPage = END_PAGE;
            return;
        }

        this.hasNextPage = currentPage < calcTotalPage(totalCount, size);
        this.nextPage = hasNextPage ? currentPage + 1 : END_PAGE;
    }

    private double calcTotalPage(int totalCount, int size) {
        return Math.ceil(totalCount / size);
    }
}
