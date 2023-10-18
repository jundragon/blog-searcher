package org.jundragon.blog.application.port.in;

import lombok.Builder;

@Builder
public record SearchTopPopularKeywordCommand(long top) {

}
