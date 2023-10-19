package org.jundragon.blogsearcher.blogsource.factory;

import org.jundragon.blogsearcher.blogsource.BlogSourceKakaoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogSourceKakaoClientClientFactory implements BlogSourceClientFactory {

    private final String baseUrl;
    private final String blogSearchPath;
    private final String restApiKey;

    public BlogSourceKakaoClientClientFactory(
        @Value("${blog-source.kakao.base-url}") String baseUrl,
        @Value("${blog-source.kakao.blog-search-path}") String blogSearchPath,
        @Value("${blog-source.kakao.auth.rest-api-key}") String restApiKey) {
        this.baseUrl = baseUrl;
        this.blogSearchPath = blogSearchPath;
        this.restApiKey = restApiKey;
    }

    @Override
    public BlogSourceOpenApiClient create() {
        return BlogSourceKakaoClient.builder()
            .baseUrl(baseUrl)
            .blogSourcePath(blogSearchPath)
            .restApiKey(restApiKey)
            .build();
    }
}
