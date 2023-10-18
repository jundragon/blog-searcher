package org.jundragon.blog.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Blog {

    private final List<BlogDocument> documents;
}
