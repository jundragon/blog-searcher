package org.jundragon.blogsearcher.core.blog.application.port.output.dto;

import lombok.Builder;
import org.jundragon.blogsearcher.core.blog.domain.BlogSearchSortType;

@Builder
public record BlogSourceRequest(String keyword, BlogSearchSortType sortType, int page, int size) {

}
