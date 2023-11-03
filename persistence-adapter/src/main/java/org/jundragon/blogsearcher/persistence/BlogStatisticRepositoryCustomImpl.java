package org.jundragon.blogsearcher.persistence;

import static org.jundragon.blogsearcher.persistence.entity.QBlogKeywordEntity.blogKeywordEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.jundragon.blogsearcher.persistence.entity.BlogKeywordEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BlogStatisticRepositoryCustomImpl extends QuerydslRepositorySupport implements
    BlogStatisticRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public BlogStatisticRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(BlogKeywordEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<BlogKeywordEntity> findByKeyword(String keyword) {
        return jpaQueryFactory.selectFrom(blogKeywordEntity)
            .where(blogKeywordEntity.keyword.eq(keyword))
            .stream().findAny();
    }

    @Override
    public List<BlogKeywordEntity> getKeywordOrderByCountDescTop(Long top) {
        return jpaQueryFactory.selectFrom(blogKeywordEntity)
            .orderBy(blogKeywordEntity.count.desc(), blogKeywordEntity.modifiedAt.desc())
            .limit(top)
            .fetch();
    }
}
