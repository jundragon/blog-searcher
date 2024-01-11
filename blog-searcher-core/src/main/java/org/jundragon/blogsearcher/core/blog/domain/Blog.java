package org.jundragon.blogsearcher.core.blog.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 카카오의 블로그 조회 응답을 기준으로 만들어진 도메인
 */
@Getter
@Builder
@RequiredArgsConstructor
public class Blog {

    private final List<BlogDocument> documents;
    private final Pagination pagination;
}
