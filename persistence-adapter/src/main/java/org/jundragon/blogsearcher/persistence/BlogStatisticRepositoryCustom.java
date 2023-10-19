package org.jundragon.blogsearcher.persistence;

import java.util.List;
import java.util.Optional;
import org.jundragon.blogsearcher.persistence.entity.BlogKeywordEntity;

public interface BlogStatisticRepositoryCustom {

    Optional<BlogKeywordEntity> findByKeyword(String keyword);

    List<BlogKeywordEntity> getKeywordOrderByCountDescTop(Long top);
}
