package org.jundragon.blog.application.service;

import java.util.Optional;
import org.jundragon.blog.application.port.in.IncreaseKeywordCountCommand;
import org.jundragon.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.blog.domain.BlogKeywordCount;
import org.jundragon.blog.mock.FakeBlogStatisticRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class BlogStatisticCommandServiceTest {

    BlogStatisticCommandService blogStatisticCommandService;

    @Mock
    BlogStatisticRepository blogStatisticRepository = new FakeBlogStatisticRepository();

    @BeforeEach
    void init() {
        this.blogStatisticCommandService = BlogStatisticCommandService.builder()
            .blogStatisticRepository(blogStatisticRepository)
            .build();
    }

    @Test
    void 키워드가_카운팅_된다() {
        // given
        blogStatisticRepository.save(BlogKeywordCount.builder()
            .keyword("아를의평원")
            .build()
        );

        var command = IncreaseKeywordCountCommand.builder()
            .keyword("아를의평원")
            .build();

        // when
        blogStatisticCommandService.increaseKeywordCount(command);
        Optional<BlogKeywordCount> byKeyword = blogStatisticRepository.findByKeyword("아를의평원");

        // then
        Assertions.assertEquals(true, byKeyword.isPresent());
        Assertions.assertEquals(2, byKeyword.get().getCount());
    }

    @Test
    void 키워드가_새로_생성된다() {
        // given
        var command = IncreaseKeywordCountCommand.builder()
            .keyword("아를의평원")
            .build();

        // when
        blogStatisticCommandService.increaseKeywordCount(command);
        Optional<BlogKeywordCount> byKeyword = blogStatisticRepository.findByKeyword("아를의평원");

        // then
        Assertions.assertEquals(true, byKeyword.isPresent());
        Assertions.assertEquals(1, byKeyword.get().getCount());
    }

}