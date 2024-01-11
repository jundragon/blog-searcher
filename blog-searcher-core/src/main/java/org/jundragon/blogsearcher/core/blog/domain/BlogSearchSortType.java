package org.jundragon.blogsearcher.core.blog.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlogSearchSortType {
    ACCURACY("accuracy", "sim", "정확도순"),
    RECENCY("recency", "date", "최신순"),
    ;

    private final String kakaoCode;
    private final String naverCode;
    private final String desc;
}
