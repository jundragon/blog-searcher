package org.jundragon.blogsearcher.blogsource.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.jundragon.blogsearcher.core.blog.domain.BlogDocument;

@Builder
public record KakaoBlogSearchResponse(
    Meta meta, // 응답 관련 정보
    List<Document> documents // 응답 결과
) implements BlogConvert {

    @Override
    public Blog to() {
        return Blog.builder()
            .documents(documents.stream().map(Document::to).collect(Collectors.toList()))
            .build();
    }

    @Builder
    public record Meta(
        @JsonProperty("total_count") Integer totalCount, // 검색된 문서 수
        @JsonProperty("pageable_count") Integer pageableCount, // total_count 중 노출 가능 문서 수
        @JsonProperty("is_end") Boolean isEnd // 현재 페이지가 마지막 페이지인지 여부
    ) {

    }

    @Builder
    public record Document(
        String title, // 블로그 글 제목
        String contents, // 블로그 글 요약
        String url, // 블로그 글 URL
        @JsonProperty("blogname") String blogName, // 블로그의 이름
        String thumbnail, // 검색 시스템에서 추출한 대표 미리보기 이미지
        ZonedDateTime datetime // 블로그 글 작성시간
    ) implements BlogDocumentConvert {

        @Override
        public BlogDocument to() {
            return BlogDocument.builder()
                .title(title)
                .contents(contents)
                .url(url)
                .blogName(blogName)
                .thumbnail(thumbnail)
                .createdAt(datetime.toLocalDateTime())
                .build();
        }
    }
}
