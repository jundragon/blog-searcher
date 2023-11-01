package org.jundragon.blogsearcher.blogsource;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceOpenApiClient;
import org.jundragon.blogsearcher.blogsource.request.BlogSearchRequest;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogSource;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogSourceRequest;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlogSourceAdapter implements BlogSource {

    private final List<BlogSourceOpenApiClient> blogSourceOpenApiClient;

    @CircuitBreaker(name = "blogsource", fallbackMethod = "searchBlogDocumentsFallback")
    @Override
    public Mono<Blog> searchBlogDocuments(BlogSourceRequest request) {
        return searchBlogDocuments(request, blogSourceOpenApiClient.get(0));
    }

    public Mono<Blog> searchBlogDocumentsFallback(BlogSourceRequest request, Throwable e) {
        log.warn("{}에 장애가 발생하여 {}로 변경합니다.",
            blogSourceOpenApiClient.get(0).getBlogSourceName(),
            blogSourceOpenApiClient.get(1).getBlogSourceName());
        log.warn("[{}] {}", UUID.randomUUID(), e.getMessage());
        return searchBlogDocuments(request, blogSourceOpenApiClient.get(1));
    }

    private Mono<Blog> searchBlogDocuments(BlogSourceRequest request, BlogSourceOpenApiClient blogSourceClient) {
        return blogSourceClient.searchBlogDocuments(BlogSearchRequest.builder()
            .query(request.keyword())
            .sort(request.sortType())
            .size(request.size())
            .page(request.page())
            .build());
    }
}
