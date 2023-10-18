package org.jundragon.blog.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BlogKeywordCount {

    private final String keyword;
    private long count;

    @Builder
    public BlogKeywordCount(String keyword) {
        this.keyword = keyword;
        this.count = 1;
    }

    public void increase() {
        this.count++;
    }
}
