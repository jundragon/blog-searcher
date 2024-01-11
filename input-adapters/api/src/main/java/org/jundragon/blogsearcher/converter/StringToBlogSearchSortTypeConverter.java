package org.jundragon.blogsearcher.converter;

import org.jundragon.blogsearcher.core.blog.domain.BlogSearchSortType;
import org.springframework.core.convert.converter.Converter;

public class StringToBlogSearchSortTypeConverter implements Converter<String, BlogSearchSortType> {

    @Override
    public BlogSearchSortType convert(String source) {
        return BlogSearchSortType.valueOf(source.trim().toUpperCase());
    }
}
