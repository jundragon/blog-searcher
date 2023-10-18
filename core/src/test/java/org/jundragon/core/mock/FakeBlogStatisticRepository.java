package org.jundragon.core.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jundragon.core.blog.application.port.out.BlogStatisticRepository;
import org.jundragon.core.blog.domain.BlogKeyword;

public class FakeBlogStatisticRepository implements BlogStatisticRepository {

    private final List<BlogKeyword> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(BlogKeyword blogKeyword) {
        data.add(blogKeyword);
    }

    @Override
    public Optional<BlogKeyword> findByKeyword(String keyword) {
        return data.stream().filter(
            item -> item.getKeyword().equals(keyword)
        ).findAny();
    }

    @Override
    public List<BlogKeyword> getKeywordOrderByCountDescTop(Long top) {
        return data.stream()
            .sorted(Comparator.comparing(BlogKeyword::getCount).reversed())
            .limit(top)
            .collect(Collectors.toList());
    }
}
