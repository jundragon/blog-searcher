package org.jundragon.core.blog.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BlogKeywordTest {

    @Test
    void BlogKeyword객체_생성시_Count가_없으면_기본값이_설정된다() {
        // given
        // when
        BlogKeyword actual = BlogKeyword.builder()
            .keyword("아그리콜라")
            .count(null)
            .build();

        // then
        Assertions.assertEquals(1L, actual.getCount());
    }

}