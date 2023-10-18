package org.jundragon.blog.application.service;

import java.util.Optional;
import org.jundragon.blog.application.port.in.IncreaseKeywordCountCommand;
import org.jundragon.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.blog.domain.BlogKeyword;
import org.jundragon.blog.mock.FakeBlogStatisticRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlogStatisticCommandServiceTest {

    BlogStatisticCommandService blogStatisticCommandService;

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
        blogStatisticRepository.save(BlogKeyword.builder()
            .keyword("아를의평원")
            .build()
        );

        var command = IncreaseKeywordCountCommand.builder()
            .keyword("아를의평원")
            .build();

        // when
        blogStatisticCommandService.increaseKeywordCount(command);
        Optional<BlogKeyword> byKeyword = blogStatisticRepository.findByKeyword("아를의평원");

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
        Optional<BlogKeyword> byKeyword = blogStatisticRepository.findByKeyword("아를의평원");

        // then
        Assertions.assertEquals(true, byKeyword.isPresent());
        Assertions.assertEquals(1, byKeyword.get().getCount());
    }

    // 10 개 이상 조회는 되지 않는다 (bean validation 에서 검증해도 될듯)

}