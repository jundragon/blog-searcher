package org.jundragon.blog.application.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.blog.application.port.in.BlogSearchCommand;
import org.jundragon.blog.application.port.in.IncreaseKeywordCountCommand;
import org.jundragon.blog.application.port.out.BlogResponse;
import org.jundragon.blog.application.port.out.BlogSource;
import org.jundragon.blog.application.port.out.BlogSourceRequest;
import org.jundragon.blog.domain.Blog;
import org.jundragon.common.annotation.FacadeService;

@FacadeService // 파사드 서비스는 다른 서비스에서 참조하지 않도록 주의
@Builder
@RequiredArgsConstructor
public class BlogSearchService {

    private final BlogSource blogSource;

    private final BlogStatisticCommandService blogStatisticCommandService;

    public BlogResponse search(BlogSearchCommand command) {
        // 인기검색어 키워드 통계용 키워드 카운트
        blogStatisticCommandService.increaseKeywordCount(
            IncreaseKeywordCountCommand.builder()
                .keyword(command.keyword())
                .build()
        );

        // 블로그 소스 검색 응답
        Blog blog = blogSource.searchBlogDocuments(
            BlogSourceRequest.builder()
                .keyword(command.keyword())
                .sortType(command.sortType())
                .page(command.page())
                .size(command.size())
                .build()
        );

        return BlogResponse.from(blog);
    }

}
