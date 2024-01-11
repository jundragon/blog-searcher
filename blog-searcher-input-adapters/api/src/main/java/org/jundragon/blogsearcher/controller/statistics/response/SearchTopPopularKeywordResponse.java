package org.jundragon.blogsearcher.controller.statistics.response;

import lombok.Builder;
import org.jundragon.blogsearcher.core.blog.domain.BlogKeyword;

@Builder
public record SearchTopPopularKeywordResponse(String keyword, Long count) {

    public static SearchTopPopularKeywordResponse from(BlogKeyword blogKeyword) {
        return SearchTopPopularKeywordResponse.builder()
            .keyword(blogKeyword.getKeyword())
            .count(blogKeyword.getCount())
            .build();
    }
}
