package org.jundragon.blogsearcher.blogsource;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceOpenApiClient;
import org.jundragon.blogsearcher.blogsource.request.BlogSearchRequest;
import org.jundragon.blogsearcher.core.blog.application.port.out.BlogSource;
import org.jundragon.blogsearcher.core.blog.application.port.out.BlogSourceRequest;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlogSourceAdapter implements BlogSource {

    private final List<BlogSourceOpenApiClient> blogSourceOpenApiClient;

    // TODO : kakao or naver Route 하는 기능 추가 필요

    @Override
    public Blog searchBlogDocuments(BlogSourceRequest request) {
        return blogSourceOpenApiClient.get(0).searchBlogDocuments(BlogSearchRequest.builder()
                .query(request.keyword())
                .sort(request.sortType())
                .size(request.size())
                .page(request.page())
                .build())
            .block(); // FIXME : 아직은 Block ...
    }
}
