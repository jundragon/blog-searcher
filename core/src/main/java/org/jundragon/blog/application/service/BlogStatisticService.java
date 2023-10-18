package org.jundragon.blog.application.service;

import java.util.Optional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.blog.application.port.in.IncreaseKeywordCountCommand;
import org.jundragon.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.blog.domain.BlogKeywordCount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Builder
@RequiredArgsConstructor
public class BlogStatisticService {

    private final BlogStatisticRepository blogStatisticRepository;

    @Transactional
    public void increaseKeywordCount(IncreaseKeywordCountCommand command) {
        Optional<BlogKeywordCount> byKeyword = blogStatisticRepository.findByKeyword(command.keyword());
        byKeyword.ifPresentOrElse(
            BlogKeywordCount::increase,
            () -> createNewKeywordCount(command)
        );
    }

    private void createNewKeywordCount(IncreaseKeywordCountCommand command) {
        blogStatisticRepository.save(
            BlogKeywordCount.builder()
                .keyword(command.keyword())
                .build()
        );
    }
}
