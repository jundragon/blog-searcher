package org.jundragon.blog.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BlogKeyword {

    private final String keyword;
    private long count;

    @Builder
    public BlogKeyword(String keyword) {
        this.keyword = keyword;
        this.count = 1;
    }

    public void increase() {
        this.count++;
    }
}
