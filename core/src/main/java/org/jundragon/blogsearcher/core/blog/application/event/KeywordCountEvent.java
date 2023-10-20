package org.jundragon.blogsearcher.core.blog.application.event;

import lombok.Builder;

@Builder
public record KeywordCountEvent(String keyword, Integer count) implements BlogStatisticEvent {

}
