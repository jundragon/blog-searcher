package org.jundragon.blogsearcher.controller.search;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import org.jundragon.blogsearcher.api.ApiResponse;
import org.jundragon.blogsearcher.core.blog.application.port.out.BlogResponse;
import org.jundragon.blogsearcher.core.blog.application.service.BlogSearchService;
import org.jundragon.blogsearcher.core.blog.domain.BlogDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = BlogSearchController.class)
class BlogSearchControllerTest {

    @MockBean
    BlogSearchService blogSearchService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void SUCCESS_블로그_조회_테스트_성공() {

        when(blogSearchService.search(any()))
            .thenReturn(Mono.just(BlogResponse.builder()
                .build()));

        webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/api/v1/blogs")
                .queryParam("keyword", "검색키워드")
                .build())
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void SUCCESS_블로그_조회_테스트_성공_응답확인() {

        BlogResponse blogResponse = BlogResponse.builder()
            .documents(
                List.of(
                    BlogDocument.builder().title("테스트블로그1").build(),
                    BlogDocument.builder().title("테스트블로그2").build()
                )).build();

        when(blogSearchService.search(any()))
            .thenReturn(Mono.just(blogResponse));

        webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/api/v1/blogs")
                .queryParam("keyword", "검색키워드")
                .build())
            .exchange()
            .expectStatus().isOk()
            .expectBody(ApiResponse.class).value(
                response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertNotNull(response.body());
                });

    }

    @Test
    void ERROR_500_블로그_조회_테스트_Keyword가_없으면_서버오류발생() {

        when(blogSearchService.search(any()))
            .thenReturn(Mono.just(BlogResponse.builder()
                .build()));

        webTestClient.get()
            .uri("/api/v1/blogs")
            .exchange()
            .expectStatus()
            .is5xxServerError();
    }
}