package org.jundragon.blogsearcher.core.blog.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PaginationTest {

    @Test
    void 페이지를_계산할_수_있다() {
        // given
        Pagination actual = Pagination.builder()
            .totalCount(1000)
            .size(10)
            .currentPage(1)
            .build();

        // when
        // then
        Assertions.assertEquals(true, actual.isHasNextPage());
        Assertions.assertEquals(2, actual.getNextPage());
    }

    @Test
    void 마지막페이지를_계산할_수_있다() {
        // given
        Pagination actual = Pagination.builder()
            .totalCount(1000)
            .size(10)
            .currentPage(100)
            .build();

        // when
        // then
        Assertions.assertEquals(false, actual.isHasNextPage());
        Assertions.assertEquals(-1, actual.getNextPage());
    }

    @Test
    void 마지막페이지를_계산할_수_있다_경계값() {
        // given
        Pagination actual = Pagination.builder()
            .totalCount(1001)
            .size(10)
            .currentPage(100)
            .build();

        // when
        // then
        Assertions.assertEquals(true, actual.isHasNextPage());
        Assertions.assertEquals(101, actual.getNextPage());
    }

    @Test
    void currentPage가_NULL일때_페이지를_계산할_수_없다() {
        // given
        Pagination actual = Pagination.builder()
            .totalCount(1001)
            .size(10)
            .currentPage(null)
            .build();

        // when
        // then
        Assertions.assertEquals(false, actual.isHasNextPage());
        Assertions.assertEquals(-1, actual.getNextPage());
    }

    @Test
    void totalCount가_NULL일때_페이지를_계산할_수_없다() {
        // given
        Pagination actual = Pagination.builder()
            .totalCount(null)
            .size(10)
            .currentPage(1)
            .build();

        // when
        // then
        Assertions.assertEquals(false, actual.isHasNextPage());
        Assertions.assertEquals(-1, actual.getNextPage());
    }
}