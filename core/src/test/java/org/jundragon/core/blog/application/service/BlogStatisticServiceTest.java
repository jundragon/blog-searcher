package org.jundragon.core.blog.application.service;

import java.util.List;
import org.jundragon.core.blog.application.port.in.IncreaseKeywordCountCommand;
import org.jundragon.core.blog.application.port.in.SearchTopPopularKeywordCommand;
import org.jundragon.core.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.core.blog.domain.BlogKeyword;
import org.jundragon.core.mock.FakeBlogStatisticRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlogStatisticServiceTest {

    BlogStatisticService blogStatisticService;
    BlogStatisticCommandService blogStatisticCommandService;

    BlogStatisticRepository blogStatisticRepository = new FakeBlogStatisticRepository();

    @BeforeEach
    void init() {
        this.blogStatisticService = BlogStatisticService.builder()
            .blogStatisticRepository(blogStatisticRepository)
            .build();
        this.blogStatisticCommandService = BlogStatisticCommandService.builder()
            .blogStatisticRepository(blogStatisticRepository)
            .build();
    }

    @Test
    void 카운트가_많은_순서대로_정렬되어_조회된다() {
        // given
        var command = SearchTopPopularKeywordCommand.builder()
            .top(10)
            .build();

        var firstKeyword = IncreaseKeywordCountCommand.builder()
            .keyword("아를의평원")
            .build();

        var secondKeyword = IncreaseKeywordCountCommand.builder()
            .keyword("오딘을위하여")
            .build();

        var mostKeyword = IncreaseKeywordCountCommand.builder()
            .keyword("글룸헤이븐")
            .build();

        // firstKeyword 5
        for (int i = 0; i < 5; i++) {
            blogStatisticCommandService.increaseKeywordCount(firstKeyword);
        }

        // secondKeyword 3
        for (int i = 0; i < 3; i++) {
            blogStatisticCommandService.increaseKeywordCount(secondKeyword);
        }

        // secondKeyword 10
        for (int i = 0; i < 10; i++) {
            blogStatisticCommandService.increaseKeywordCount(mostKeyword);
        }

        // when
        List<BlogKeyword> topPopularKeywords = blogStatisticService.getTopPopularKeyword(command);

        // then
        Assertions.assertEquals(10, topPopularKeywords.get(0).getCount());
        Assertions.assertEquals("글룸헤이븐", topPopularKeywords.get(0).getKeyword());
        Assertions.assertEquals(5, topPopularKeywords.get(1).getCount());
        Assertions.assertEquals("아를의평원", topPopularKeywords.get(1).getKeyword());
        Assertions.assertEquals(3, topPopularKeywords.get(2).getCount());
        Assertions.assertEquals("오딘을위하여", topPopularKeywords.get(2).getKeyword());
    }

    @Test
    void SearchTopPopularKeywordCommand_수_만큼_조회된다() {
        // given
        var command = SearchTopPopularKeywordCommand.builder()
            .top(10)
            .build();

        for (int i = 0; i < 20; i++) {
            blogStatisticCommandService.increaseKeywordCount(IncreaseKeywordCountCommand.builder()
                .keyword(String.format("글룸헤이븐%d", i))
                .build());
        }

        // when
        List<BlogKeyword> topPopularKeywords = blogStatisticService.getTopPopularKeyword(command);

        // then
        Assertions.assertEquals(10, topPopularKeywords.size());
    }

}