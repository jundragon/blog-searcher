package org.jundragon.blogsearcher.persistence;

import org.jundragon.blogsearcher.persistence.entity.BlogKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogStatisticJpaRepository extends JpaRepository<BlogKeywordEntity, Long>,
    BlogStatisticRepositoryCustom {

}
