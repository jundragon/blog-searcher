package org.jundragon.blogsearcher.core.blog.application.service;

import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.core.blog.application.event.KeywordCountEvent;
import org.jundragon.blogsearcher.core.blog.application.event.KeywordEventPublisher;
import org.jundragon.blogsearcher.core.blog.application.port.input.BlogSearchCommand;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogResponse;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogSource;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogSourceRequest;
import org.jundragon.blogsearcher.core.blog.utils.tokenizer.KeywordTokenizer;
import org.jundragon.blogsearcher.core.common.annotation.FacadeService;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

@FacadeService
@Builder
@RequiredArgsConstructor
public class BlogSearchService {

    private final BlogSource blogSource;
    private final KeywordEventPublisher keywordEventPublisher;
    private final BlogStatisticCommandService blogStatisticCommandService;
    private final KeywordTokenizer tokenizer;

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

    private void publish(String message) {
        // 인기검색어 키워드 통계용 키워드 카운트 이벤트 발생
        // 키워드는 토큰화 하여 저장하여 수집한다.
        List<String> tokens = tokenizer.tokenize(message);
        KeywordCountEvent keywordCountEvent = KeywordCountEvent.builder()
            .keywords(tokens)
            .build();
        keywordEventPublisher.publish(keywordCountEvent);
    }
}
