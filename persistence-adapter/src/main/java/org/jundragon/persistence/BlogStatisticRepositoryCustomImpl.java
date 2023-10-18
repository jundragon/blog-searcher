package org.jundragon.persistence;

import static com.querydsl.jpa.JPAExpressions.selectFrom;
import static org.jundragon.persistence.entity.QBlogKeywordEntity.blogKeywordEntity;

import java.util.List;
import java.util.Optional;
import org.jundragon.persistence.entity.BlogKeywordEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BlogStatisticRepositoryCustomImpl extends QuerydslRepositorySupport implements
    BlogStatisticRepositoryCustom {


    public BlogStatisticRepositoryCustomImpl() {
        super(BlogKeywordEntity.class);
    }

    @Override
    public Optional<BlogKeywordEntity> findByKeyword(String keyword) {
        return selectFrom(blogKeywordEntity)
            .where(blogKeywordEntity.keyword.eq(keyword)).stream().findAny();
    }

    @Override
    public List<BlogKeywordEntity> getKeywordOrderByCountDescTop(Long top) {
        return selectFrom(blogKeywordEntity)
            .orderBy(blogKeywordEntity.count.desc())
            .limit(top)
            .fetch();
    }
}
