package org.jundragon.blog.domain;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BlogDocument(String title, String contents, String url, String blogName, String thumbnail,
                           LocalDateTime createdAt) {

}
