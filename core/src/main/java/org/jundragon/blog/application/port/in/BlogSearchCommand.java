package org.jundragon.blog.application.port.in;

import lombok.Builder;
import org.jundragon.blog.domain.BlogSearchSortType;

@Builder
public record BlogSearchCommand(String keyword, BlogSearchSortType sortType, int page, int size) {

}
