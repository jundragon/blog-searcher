package org.jundragon.blogsearcher.core.blog.application.port.out;

import java.util.List;
import java.util.Optional;
import org.jundragon.blogsearcher.core.blog.domain.BlogKeyword;

public interface BlogStatisticRepository {

    void save(BlogKeyword blogKeyword);

    Optional<BlogKeyword> findByKeyword(String keyword);

    List<BlogKeyword> getKeywordOrderByCountDescTop(Long top);
}
