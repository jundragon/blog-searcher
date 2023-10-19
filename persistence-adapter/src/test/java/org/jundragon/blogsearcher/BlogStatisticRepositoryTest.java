package org.jundragon.blogsearcher;

import java.util.List;
import java.util.Optional;
import org.jundragon.blogsearcher.persistence.BlogStatisticJpaRepository;
import org.jundragon.blogsearcher.persistence.entity.BlogKeywordEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql(
    scripts = {"classpath:testdata/keywordCount.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class BlogStatisticRepositoryTest extends MiddleTest {

    @Autowired
    BlogStatisticJpaRepository blogStatisticJpaRepository;

    @Test
    void 키워드로_조회_할_수_있다() {
        Optional<BlogKeywordEntity> actual = blogStatisticJpaRepository.findByKeyword("테라포밍마스");

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals("테라포밍마스", actual.get().getKeyword());
        Assertions.assertEquals(11, actual.get().getCount());
    }

    @Test
    void 인기검색어를_조회_할_수_있다() {
        List<BlogKeywordEntity> actual = blogStatisticJpaRepository.getKeywordOrderByCountDescTop(
            10L);

        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals("아그리콜라", actual.get(0).getKeyword());
    }
}