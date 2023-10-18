package org.jundragon.persistence;

import java.util.List;
import java.util.Optional;
import org.jundragon.persistence.entity.BlogKeywordEntity;

public interface BlogStatisticRepositoryCustom {

    Optional<BlogKeywordEntity> findByKeyword(String keyword);

    List<BlogKeywordEntity> getKeywordOrderByCountDescTop(Long top);
}
