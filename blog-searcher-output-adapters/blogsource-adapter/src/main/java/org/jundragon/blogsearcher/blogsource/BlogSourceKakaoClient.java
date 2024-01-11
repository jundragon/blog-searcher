package org.jundragon.blogsearcher.blogsource;

import java.util.stream.Collectors;
import lombok.Builder;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceOpenApiClient;
import org.jundragon.blogsearcher.blogsource.request.BlogSearchRequest;
import org.jundragon.blogsearcher.blogsource.response.KakaoBlogSearchResponse;
import org.jundragon.blogsearcher.blogsource.response.KakaoBlogSearchResponse.Document;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.jundragon.blogsearcher.core.blog.domain.Pagination;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BlogSourceKakaoClient implements BlogSourceOpenApiClient {

    private final static String NAME = "카카오 블로그 소스";

    private final WebClient webClient;
    private String baseUrl;
    private String blogSourcePath;
    private String restApiKey;

    @Override
    public String getBlogSourceName() {
        return NAME;
    }

    @Builder
    public BlogSourceKakaoClient(String baseUrl, String blogSourcePath, String restApiKey) {
        this.baseUrl = baseUrl;
        this.blogSourcePath = blogSourcePath;
        this.restApiKey = restApiKey;

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
                    .queryParam("sort", request.sort().getKakaoCode())
                    .queryParam("page", request.page())
                    .queryParam("size", request.size())
                    .build())
            .header("Authorization", "KakaoAK " + restApiKey)
            .retrieve()
            .bodyToMono(KakaoBlogSearchResponse.class)
            .map(response -> convert(request, response));
    }

    private Blog convert(BlogSearchRequest request, KakaoBlogSearchResponse response) {
        return Blog.builder()
            .documents(
                response.documents().stream()
                    .map(Document::to).collect(Collectors.toList()))
            .pagination(
                Pagination.builder()
                    .currentPage(request.page())
                    .size(request.size())
                    .totalCount(response.meta().pageableCount())
                    .build())
            .build();
    }
}
