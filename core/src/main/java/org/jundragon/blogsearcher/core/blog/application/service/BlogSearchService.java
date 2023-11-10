package org.jundragon.blogsearcher.core.blog.application.service;

import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.core.blog.application.event.KeywordCountEvent;
import org.jundragon.blogsearcher.core.blog.application.event.KeywordEventPublisher;
import org.jundragon.blogsearcher.core.blog.application.port.input.BlogSearchCommand;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogResponse;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogSource;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogSourceRequest;
import org.jundragon.blogsearcher.core.common.annotation.FacadeService;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

@FacadeService
@RequiredArgsConstructor
public class BlogSearchService {

    private final BlogSource blogSource;
    private final KeywordEventPublisher keywordEventPublisher;

    @Transactional
    public Mono<BlogResponse> search(BlogSearchCommand command) {

        // 블로그 소스 검색 응답
        return blogSource.searchBlogDocuments(
                BlogSourceRequest.builder()
                    .keyword(command.keyword())
                    .sortType(command.sort())
                    .page(command.page())
                    .size(command.size())
                    .build())
            .map(BlogResponse::from).cache()
            .doFinally(f -> {
                if (f.equals(SignalType.ON_COMPLETE)) {
                    publish(command.keyword());
                }
            });
    }

    /**
     * 인기검색어 키워드 통계용 키워드 카운트 이벤트 발생
     *
     * @param message
     */
    private void publish(String message) {
        KeywordCountEvent keywordCountEvent = KeywordCountEvent.builder()
            .keyword(message)
            .build();
        keywordEventPublisher.publish(keywordCountEvent);
    }
}
