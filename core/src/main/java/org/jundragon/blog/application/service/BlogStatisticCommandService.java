package org.jundragon.blog.application.service;

import java.util.Optional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.blog.application.port.in.IncreaseKeywordCountCommand;
import org.jundragon.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.blog.domain.BlogKeyword;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Builder
@RequiredArgsConstructor
public class BlogStatisticCommandService {

    private final BlogStatisticRepository blogStatisticRepository;

    @Transactional
    public void increaseKeywordCount(IncreaseKeywordCountCommand command) {
        Optional<BlogKeyword> byKeyword = blogStatisticRepository.findByKeyword(command.keyword());
        byKeyword.ifPresentOrElse(
            BlogKeyword::increase,
            () -> createNewKeywordCount(command)
        );
    }

    private void createNewKeywordCount(IncreaseKeywordCountCommand command) {
        blogStatisticRepository.save(
            BlogKeyword.builder()
                .keyword(command.keyword())
                .build()
        );
    }
}
