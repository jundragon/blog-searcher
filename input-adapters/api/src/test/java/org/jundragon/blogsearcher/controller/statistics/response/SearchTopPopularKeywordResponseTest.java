package org.jundragon.blogsearcher.controller.statistics.response;

import org.jundragon.blogsearcher.core.blog.domain.BlogKeyword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchTopPopularKeywordResponseTest {

    @Test
    void BlogKeyword객체로_SearchTopPopularKeywordResponse를_생성할_수_있다() {
        // given
        BlogKeyword blogKeyword = BlogKeyword.builder()
            .keyword("테라포밍마스")
            .count(10L)
            .build();

        // when
        SearchTopPopularKeywordResponse actual = SearchTopPopularKeywordResponse.from(blogKeyword);

        // then
        Assertions.assertEquals("테라포밍마스", actual.keyword());
        Assertions.assertEquals(10L, actual.count());
    }
}