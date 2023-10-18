package org.jundragon.blog.application.service;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.jundragon.blog.application.port.in.BlogSearchCommand;
import org.jundragon.blog.application.port.out.BlogSource;
import org.jundragon.blog.application.port.out.BlogSourceRequest;
import org.jundragon.blog.domain.Blog;
import org.jundragon.blog.domain.BlogDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlogSearchServiceTest {

    @Mock
    BlogSource blogSource;
    BlogSearchService blogSearchService;

    @BeforeEach
    void init() {
        this.blogSearchService = BlogSearchService.builder()
            .blogSource(blogSource)
            .build();
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

        when(blogSource.searchBlogDocuments(isA(BlogSourceRequest.class)))
            .thenReturn(
                Blog.builder()
                    .documents(documents)
                    .build()
            );

        var command = BlogSearchCommand.builder()
            .keyword("카카오뱅크")
            .build();

        // when
        var actual = blogSearchService.search(command);

        // then
        Assertions.assertEquals(documents.size(), actual.getDocuments().size());
        Assertions.assertEquals(documents.get(0).contents(), actual.getDocuments().get(0).contents());
    }

//    @Test
//    void 블로그_조회시_키워드를_저장해야_한다() {
//
//    }
}