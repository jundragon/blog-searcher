package org.jundragon.blogsearcher.core.blog.application.port.in;

import lombok.Builder;
import org.jundragon.blogsearcher.core.blog.domain.BlogSearchSortType;

@Builder
public record BlogSearchCommand(String keyword, BlogSearchSortType sortType, int page, int size) {

}
