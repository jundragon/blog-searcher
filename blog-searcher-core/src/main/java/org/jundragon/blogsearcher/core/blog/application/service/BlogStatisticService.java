package org.jundragon.blogsearcher.core.blog.application.service;

import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.core.blog.application.port.input.dto.SearchTopPopularKeywordCommand;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogStatisticRepository;
import org.jundragon.blogsearcher.core.blog.domain.BlogKeyword;
import org.jundragon.blogsearcher.core.common.annotation.FacadeService;

@FacadeService
@Builder
@RequiredArgsConstructor
public class BlogStatisticService {

    private final BlogStatisticRepository blogStatisticRepository;

    public List<BlogKeyword> getTopPopularKeyword(SearchTopPopularKeywordCommand command) {
        return blogStatisticRepository.getKeywordOrderByCountDescTop(command.top());
    }
}
