package org.jundragon.blogsearcher.core.blog.application.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.jundragon.blogsearcher.core.blog.application.port.input.BlogSearchCommand;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogSource;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.jundragon.blogsearcher.core.blog.domain.BlogDocument;
import org.jundragon.blogsearcher.core.blog.domain.BlogSearchSortType;
import org.jundragon.blogsearcher.core.mock.DummyKeywordEventPublisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class BlogSearchServiceTest {

    @Mock
    BlogSource blogSource;
    BlogSearchService blogSearchService;

    @BeforeEach
    void init() {
        this.blogSearchService = new BlogSearchService(
            blogSource,
            new DummyKeywordEventPublisher()
        );
    }

    @Test
    void BlogSearchCommand로_블로그를_조회_할_수_있다() {

        // given
        List<BlogDocument> documents = new ArrayList<>();
        documents.add(
            BlogDocument.builder()
                .blogName("카카오뱅크")
                .contents("카카오뱅크 블로그 입니다.")
                .build()
        );

        when(blogSource.searchBlogDocuments(any()))
            .thenReturn(
                Mono.just(Blog.builder()
                    .documents(documents)
                    .build())
            );

        var command = BlogSearchCommand.builder()
            .keyword("카카오뱅크")
            .sort(BlogSearchSortType.ACCURACY)
            .build();

        // when
        var actual = blogSearchService.search(command);

        // then
        Assertions.assertEquals(documents.size(), actual.block().getDocuments().size());
        Assertions.assertEquals(documents.get(0).contents(), actual.block().getDocuments().get(0).contents());
    }

}