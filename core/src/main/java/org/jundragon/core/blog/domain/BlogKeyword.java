package org.jundragon.core.blog.domain;

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
        // count 가 0개 이하로 입력될 경우 예외 검사가 필요하다
        this.id = id;
        this.keyword = keyword;
        this.count = Objects.isNull(count) ? 1L : count;
    }

    public void increase() {
        this.count++;
    }
}
