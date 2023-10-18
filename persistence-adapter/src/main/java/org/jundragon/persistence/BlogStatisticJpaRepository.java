package org.jundragon.persistence;

import org.jundragon.persistence.entity.BlogKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogStatisticJpaRepository extends JpaRepository<BlogKeywordEntity, Long>,
    BlogStatisticRepositoryCustom {

}
