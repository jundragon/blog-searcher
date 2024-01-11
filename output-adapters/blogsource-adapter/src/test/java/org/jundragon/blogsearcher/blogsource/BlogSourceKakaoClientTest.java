package org.jundragon.blogsearcher.blogsource;

import org.jundragon.blogsearcher.blogsource.request.BlogSearchRequest;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class BlogSourceKakaoClientTest {

    /**
     * FIXME: 구현시 동작만 테스트하는 용도입니다. mock 등을 이용해서 결정적 테스트로 변경이 필요합니다.
     */
    @Test
    void 카카오_클라이언트_테스트() {
        // given
        BlogSourceKakaoClient client = BlogSourceKakaoClient.builder()
            .baseUrl("https://dapi.kakao.com")
            .blogSourcePath("/v2/search/blog")
            .restApiKey("2e5c09a60c9094606c03bf3be3842dfc")
            .build();

        BlogSearchRequest request = BlogSearchRequest.builder()
            .query("테스트")
            .build();

        // when
        Mono<Blog> responseMono = client.searchBlogDocuments(request);

        // then
        Blog block = responseMono.block();
        Assertions.assertFalse(block.getDocuments().isEmpty());
    }

}