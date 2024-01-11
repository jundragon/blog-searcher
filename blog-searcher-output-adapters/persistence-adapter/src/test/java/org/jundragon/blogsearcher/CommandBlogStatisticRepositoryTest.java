package org.jundragon.blogsearcher;

import org.jundragon.blogsearcher.persistence.BlogStatisticJpaRepository;
import org.jundragon.blogsearcher.persistence.entity.BlogKeywordEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CommandBlogStatisticRepositoryTest extends MiddleTest {

    @Autowired
    BlogStatisticJpaRepository blogStatisticJpaRepository;

    @Test
    void 신규키워드를_저장_할_수_있다() {
        BlogKeywordEntity actual = blogStatisticJpaRepository.save(
            BlogKeywordEntity.builder()
                .keyword("새로운키워드")
                .count(1)
                .build()
        );

        Assertions.assertEquals("새로운키워드", actual.getKeyword());
        Assertions.assertEquals(1, actual.getCount());
    }
}
