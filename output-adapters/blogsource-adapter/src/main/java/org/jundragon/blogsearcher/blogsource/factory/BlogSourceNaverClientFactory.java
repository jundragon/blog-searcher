package org.jundragon.blogsearcher.blogsource.factory;

import org.jundragon.blogsearcher.blogsource.BlogSourceNaverClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogSourceNaverClientFactory implements BlogSourceClientFactory {

    private final String baseUrl;
    private final String blogSearchPath;
    private final String clientId;
    private final String clientSecret;

    public BlogSourceNaverClientFactory(
        @Value("${blog-source.naver.base-url}") String baseUrl,
        @Value("${blog-source.naver.blog-search-path}") String blogSearchPath,
        @Value("${blog-source.naver.auth.client-id}") String clientId,
        @Value("${blog-source.naver.auth.client-secret}") String clientSecret) {
        this.baseUrl = baseUrl;
        this.blogSearchPath = blogSearchPath;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public BlogSourceOpenApiClient create() {
        return BlogSourceNaverClient.builder()
            .baseUrl(baseUrl)
            .blogSourcePath(blogSearchPath)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();
    }
}
