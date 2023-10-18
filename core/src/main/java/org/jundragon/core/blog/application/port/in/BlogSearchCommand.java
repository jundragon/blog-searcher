package org.jundragon.core.blog.application.port.in;

import lombok.Builder;
import org.jundragon.core.blog.domain.BlogSearchSortType;

@Builder
public record BlogSearchCommand(String keyword, BlogSearchSortType sortType, int page, int size) {

}
