package org.jundragon.blogsearcher.blogsource.config;

import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceKakaoClientClientFactory;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceNaverClientFactory;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceOpenApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BlogSourceOpenApiConfig {

    private final BlogSourceKakaoClientClientFactory blogSourceKakaoClientFactory;
    private final BlogSourceNaverClientFactory blogSourceNaverClientFactory;

    @Bean
    public BlogSourceOpenApiClient blogSourceKakaoClient() {
        return blogSourceKakaoClientFactory.create();
    }

    @Bean
    public BlogSourceOpenApiClient blogSourceNaverClient() {
        return blogSourceNaverClientFactory.create();
    }

}
