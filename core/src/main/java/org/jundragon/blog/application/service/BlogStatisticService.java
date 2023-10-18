package org.jundragon.blog.application.service;

import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jundragon.blog.application.port.in.SearchTopPopularKeywordCommand;
import org.jundragon.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.blog.domain.BlogKeyword;
import org.jundragon.common.annotation.FacadeService;

@FacadeService
@Builder
@RequiredArgsConstructor
public class BlogStatisticService {

    private final BlogStatisticRepository blogStatisticRepository;

    public List<BlogKeyword> getTopPopularKeyword(SearchTopPopularKeywordCommand command) {
        return blogStatisticRepository.getKeywordOrderByCountDescTop(command.top());
    }
}
