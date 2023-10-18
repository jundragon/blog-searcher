package org.jundragon.blog.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jundragon.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.blog.domain.BlogKeywordCount;

public class FakeBlogStatisticRepository implements BlogStatisticRepository {

    private final List<BlogKeywordCount> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(BlogKeywordCount blogKeywordCount) {
        data.add(blogKeywordCount);
    }

    @Override
    public Optional<BlogKeywordCount> findByKeyword(String keyword) {
        return data.stream().filter(
            item -> item.getKeyword().equals(keyword)
        ).findAny();
    }

    @Override
    public List<BlogKeywordCount> findByKeywordOrderByCountDescTop(Long top) {
        return data.stream()
            .sorted(Comparator.comparing(BlogKeywordCount::getCount))
            .limit(top)
            .collect(Collectors.toList());
    }
}
