package org.jundragon.blogsearcher.core.blog.application.port.input.dto;

import lombok.Builder;

@Builder
public record SearchTopPopularKeywordCommand(long top) {

}
