package org.jundragon.blogsearcher.core.blog.application.service;

import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.core.blog.application.event.KeywordCountEvent;
import org.jundragon.blogsearcher.core.blog.application.event.KeywordEventPublisher;
import org.jundragon.blogsearcher.core.blog.application.port.in.BlogSearchCommand;
import org.jundragon.blogsearcher.core.blog.application.port.out.BlogResponse;
import org.jundragon.blogsearcher.core.blog.application.port.out.BlogSource;
import org.jundragon.blogsearcher.core.blog.application.port.out.BlogSourceRequest;
import org.jundragon.blogsearcher.core.blog.utils.TokenizeUtils;
import org.jundragon.blogsearcher.core.common.annotation.FacadeService;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@FacadeService // 파사드 서비스는 다른 서비스에서 참조하지 않도록 주의
@Builder
@RequiredArgsConstructor
public class BlogSearchService {

    private final BlogSource blogSource;
    private final BlogStatisticCommandService blogStatisticCommandService;
    private final KeywordEventPublisher keywordEventPublisher;

    @Transactional
    public Mono<BlogResponse> search(BlogSearchCommand command) {

        // 인기검색어 키워드 통계용 키워드 카운트 이벤트 발생
        // 키워드는 토큰화 하여 저장하여 수집한다.
        List<String> tokenize = TokenizeUtils.tokenize(command.keyword());
        KeywordCountEvent keywordCountEvent = KeywordCountEvent.builder()
            .keywords(tokenize)
            .build();
        keywordEventPublisher.publish(keywordCountEvent);

        // 블로그 소스 검색 응답
        return blogSource.searchBlogDocuments(
                BlogSourceRequest.builder()
                    .keyword(command.keyword())
                    .sortType(command.sort())
                    .page(command.page())
                    .size(command.size())
                    .build())
            .map(BlogResponse::from);
    }
}
