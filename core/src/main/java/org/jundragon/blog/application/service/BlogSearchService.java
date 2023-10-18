package org.jundragon.blog.application.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.blog.application.port.in.BlogSearchCommand;
import org.jundragon.blog.application.port.out.BlogResponse;
import org.jundragon.blog.application.port.out.BlogSource;
import org.jundragon.blog.application.port.out.BlogSourceRequest;
import org.jundragon.blog.domain.Blog;
import org.springframework.stereotype.Service;

@Builder
@Service
@RequiredArgsConstructor
public class BlogSearchService {

    private final BlogSource blogSource;

    public BlogResponse search(BlogSearchCommand command) {
        // TODO 인기검색어 키워드 통계 유스케이스 추가

        // TODO 블로그 소스로 부터 검색 요청 후 응답
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
