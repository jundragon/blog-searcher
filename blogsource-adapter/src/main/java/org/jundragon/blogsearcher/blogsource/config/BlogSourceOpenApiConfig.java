package org.jundragon.blogsearcher.blogsource.config;

import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceKakaoClientClientFactory;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceNaverClientFactory;
import org.jundragon.blogsearcher.blogsource.factory.BlogSourceOpenApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class BlogSourceOpenApiConfig {

    private final BlogSourceKakaoClientClientFactory blogSourceKakaoClientFactory;
    private final BlogSourceNaverClientFactory blogSourceNaverClientFactory;

    @Bean
    @Order(1) // 우선순위 가장 높음
    public BlogSourceOpenApiClient blogSourceKakaoClient() {
        return blogSourceKakaoClientFactory.create();
    }

    @Bean
    @Order(2)
    public BlogSourceOpenApiClient blogSourceNaverClient() {
        return blogSourceNaverClientFactory.create();
    }

}
