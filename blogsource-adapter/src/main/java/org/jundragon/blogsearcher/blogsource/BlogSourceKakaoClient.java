package org.jundragon.blogsearcher.blogsource;

import lombok.Builder;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceOpenApiClient;
import org.jundragon.blogsearcher.blogsource.request.BlogSearchRequest;
import org.jundragon.blogsearcher.blogsource.response.KakaoBlogSearchResponse;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BlogSourceKakaoClient implements BlogSourceOpenApiClient {

    private final WebClient webClient;
    private String baseUrl;
    private String blogSourcePath;
    private String restApiKey;

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
            .map(KakaoBlogSearchResponse::to);
    }
}
