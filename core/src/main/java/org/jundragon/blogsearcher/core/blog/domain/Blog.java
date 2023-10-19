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

    // TODO : 페이징 정보 추가 (적어도 카카오, 네이버 두 제공자에서 제공하는 정보로 만들어낼 수 있는 것으로)

    private final List<BlogDocument> documents;
}
