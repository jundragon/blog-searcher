package org.jundragon.core.blog.application.service;

import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.core.blog.application.port.in.SearchTopPopularKeywordCommand;
import org.jundragon.core.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.core.blog.domain.BlogKeyword;
import org.jundragon.core.common.annotation.FacadeService;

@FacadeService
@Builder
@RequiredArgsConstructor
public class BlogStatisticService {

    private final BlogStatisticRepository blogStatisticRepository;

    public List<BlogKeyword> getTopPopularKeyword(SearchTopPopularKeywordCommand command) {
        return blogStatisticRepository.getKeywordOrderByCountDescTop(command.top());
    }
}
