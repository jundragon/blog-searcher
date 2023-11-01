package org.jundragon.blogsearcher.core.blog.application.port.input;

import lombok.Builder;

@Builder
public record SearchTopPopularKeywordCommand(long top) {

}
