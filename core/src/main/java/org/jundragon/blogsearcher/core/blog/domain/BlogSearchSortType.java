package org.jundragon.blogsearcher.core.blog.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlogSearchSortType {
    ACCURACY("정확도순"),
    RECENCY("최신순"),
    ;

    private final String name;
}