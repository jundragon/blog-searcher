package org.jundragon.core.blog.application.port.in;

import lombok.Builder;

@Builder
public record IncreaseKeywordCountCommand(String keyword) {

}
