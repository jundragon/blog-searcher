package org.jundragon.blogsearcher.core.blog.application.port.output.event;

import lombok.Builder;

@Builder
public record KeywordCountEvent(String keyword) implements BlogStatisticEvent {

}
