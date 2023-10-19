package org.jundragon.blogsearcher.blogsource;

import org.jundragon.blogsearcher.blogsource.request.BlogSearchRequest;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class BlogSourceNaverClientTest {

    /**
     * FIXME: 구현시 동작만 테스트하는 용도입니다. mock 등을 이용해서 결정적 테스트로 변경이 필요합니다.
     */
    @Test
    void 네이버_클라이언트_테스트() {
        // given
        BlogSourceNaverClient client = BlogSourceNaverClient.builder()
            .baseUrl("https://openapi.naver.com")
            .blogSourcePath("/v1/search/blog")
            .clientId("gGZKyZdM3AtUIjcfMyOf")
            .clientSecret("5tdNu9ivcH")
            .build();

        BlogSearchRequest request = BlogSearchRequest.builder()
            .query("테스트")
            .sort("sim")
            .page(1)
            .size(10)
            .build();

        // when
        Mono<Blog> responseMono = client.searchBlogDocuments(request);
        // then
        Blog block = responseMono.block();
        Assertions.assertFalse(block.getDocuments().isEmpty());
    }

}