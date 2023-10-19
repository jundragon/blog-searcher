package org.jundragon.blogsearcher.core.blog.application.port.in;

import lombok.Builder;

@Builder
public record IncreaseKeywordCountCommand(String keyword) {

}
