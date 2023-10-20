package org.jundragon.blogsearcher.controller.statistics;

import org.jundragon.blogsearcher.core.blog.application.service.BlogStatisticService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = BlogStatisticsController.class)
class BlogStatisticsControllerTest {

    @MockBean
    BlogStatisticService blogStatisticService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void 정상응답_인기_키워드_검색() {

        webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/api/v1/blogs/statistics/popular")
                .queryParam("top", "10")
                .build())
            .exchange()
            .expectStatus().isOk();
    }
}