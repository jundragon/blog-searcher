package org.jundragon.blogsearcher.blogsource;

import java.util.stream.Collectors;
import lombok.Builder;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceOpenApiClient;
import org.jundragon.blogsearcher.blogsource.request.BlogSearchRequest;
import org.jundragon.blogsearcher.blogsource.response.NaverBlogSearchResponse;
import org.jundragon.blogsearcher.blogsource.response.NaverBlogSearchResponse.BlogItem;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.jundragon.blogsearcher.core.blog.domain.Pagination;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BlogSourceNaverClient implements BlogSourceOpenApiClient {

    private final static String NAME = "네이버 블로그 소스";

    private final WebClient webClient;
    private String baseUrl;
    private String blogSourcePath;
    private String clientId;
    private String clientSecret;

    @Override
    public String getBlogSourceName() {
        return NAME;
    }

    @Builder
    public BlogSourceNaverClient(String baseUrl, String blogSourcePath, String clientId, String clientSecret) {
        this.baseUrl = baseUrl;
        this.blogSourcePath = blogSourcePath;
        this.clientId = clientId;
        this.clientSecret = clientSecret;

        webClient = WebClient.builder()
            .baseUrl(this.baseUrl)
            .build();
    }

    @Override
    public Mono<Blog> searchBlogDocuments(BlogSearchRequest request) {
        return webClient.get()
            .uri(uriBuilder ->
                uriBuilder.path(blogSourcePath)
                    .queryParam("query", request.query())
                    .queryParam("sort", request.sort().getNaverCode())
                    .queryParam("page", request.page())
                    .queryParam("size", request.size())
                    .build())
            .header("X-Naver-Client-Id", clientId)
            .header("X-Naver-Client-Secret", clientSecret)
            .retrieve()
            .bodyToMono(NaverBlogSearchResponse.class)
            .map(response -> convert(request, response));
    }

    private Blog convert(BlogSearchRequest request, NaverBlogSearchResponse response) {
        return Blog.builder()
            .documents(
                response.items().stream()
                    .map(BlogItem::to).collect(Collectors.toList()))
            .pagination(
                Pagination.builder()
                    .currentPage(request.page())
                    .size(request.size())
                    .totalCount(response.total().intValue())
                    .build())
            .build();
    }
}
