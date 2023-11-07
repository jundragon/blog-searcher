package org.jundragon.blogsearcher.persistence;

import static org.jundragon.blogsearcher.persistence.config.CacheConfig.CACHE_NAME;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.core.blog.application.port.output.BlogStatisticRepository;
import org.jundragon.blogsearcher.core.blog.domain.BlogKeyword;
import org.jundragon.blogsearcher.persistence.entity.BlogKeywordEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogStatisticRepositoryImpl implements BlogStatisticRepository {

    private final BlogStatisticJpaRepository blogStatisticJpaRepository;

    @Override
    public void save(BlogKeyword blogKeyword) {
        blogStatisticJpaRepository.save(BlogKeywordEntity.from(blogKeyword));
    }

    @Override
    public Optional<BlogKeyword> findByKeyword(String keyword) {
        Optional<BlogKeywordEntity> byKeyword = blogStatisticJpaRepository.findByKeyword(keyword);
        return Optional.ofNullable(byKeyword.isPresent() ? byKeyword.get().to() : null);
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "'top_keyword'")
    public List<BlogKeyword> getKeywordOrderByCountDescTop(Long top) {
        return blogStatisticJpaRepository.getKeywordOrderByCountDescTop(top).stream()
            .map(BlogKeywordEntity::to)
            .collect(Collectors.toList());
    }
}
