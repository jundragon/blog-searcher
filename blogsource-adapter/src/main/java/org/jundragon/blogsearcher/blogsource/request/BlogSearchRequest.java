package org.jundragon.blogsearcher.blogsource.request;

import java.util.Objects;
import lombok.Builder;

public record BlogSearchRequest(String query, String sort, Integer page, Integer size) {

    @Builder
    public BlogSearchRequest(String query, String sort, Integer page, Integer size) {
        // TODO: Enum 으로 서비스 제공자 별 묶어서 처리
        this.query = query;
        this.sort = Objects.isNull(sort) ? "accuracy" : sort;
        this.page = Objects.isNull(page) ? 1 : page;
        this.size = Objects.isNull(size) ? 10 : size;
    }
}
