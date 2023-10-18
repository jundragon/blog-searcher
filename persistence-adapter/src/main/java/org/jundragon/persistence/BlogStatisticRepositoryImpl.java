package org.jundragon.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jundragon.core.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.core.blog.domain.BlogKeyword;
import org.jundragon.persistence.entity.BlogKeywordEntity;
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
    public List<BlogKeyword> getKeywordOrderByCountDescTop(Long top) {
        return blogStatisticJpaRepository.getKeywordOrderByCountDescTop(top).stream()
            .map(BlogKeywordEntity::to)
            .collect(Collectors.toList());
    }
}
