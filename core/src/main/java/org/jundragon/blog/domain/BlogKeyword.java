package org.jundragon.blog.domain;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BlogKeyword {

    private final Long id;

    private final String keyword;
    private Long count;

    @Builder
    public BlogKeyword(Long id, String keyword, Long count) {
        this.id = id;
        this.keyword = keyword;
        this.count = Objects.isNull(count) ? 1L : count;
    }

    public void increase() {
        this.count++;
    }
}
