package org.jundragon.blogsearcher.core.blog.domain;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogKeyword {

    private Long id;
    private String keyword;
    private Long count;

    @Builder
    public BlogKeyword(Long id, String keyword, Long count) {
        // TODO count 가 0개 이하로 입력될 경우 예외 검사가 필요하다
        this.id = id;
        this.keyword = keyword;
        this.count = Objects.isNull(count) ? 1L : count;
    }

    public void increase() {
        this.count++;
    }
}
