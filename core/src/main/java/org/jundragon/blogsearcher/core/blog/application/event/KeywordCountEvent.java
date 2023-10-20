package org.jundragon.blogsearcher.core.blog.application.event;

import java.util.List;
import lombok.Builder;

@Builder
public record KeywordCountEvent(List<String> keywords) implements BlogStatisticEvent {

}
