package org.jundragon.blogsearcher.core.blog.application.service;

import java.util.Optional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.core.blog.application.port.input.IncreaseKeywordCountCommand;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogStatisticRepository;
import org.jundragon.blogsearcher.core.blog.domain.BlogKeyword;
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
        if (!byKeyword.isPresent()) {
            createNewKeywordCount(command);
            return;
        }
        byKeyword.get().increase();
        blogStatisticRepository.save(byKeyword.get());
    }

    private void createNewKeywordCount(IncreaseKeywordCountCommand command) {
        blogStatisticRepository.save(
            BlogKeyword.builder()
                .keyword(command.keyword())
                .build()
        );
    }
}
