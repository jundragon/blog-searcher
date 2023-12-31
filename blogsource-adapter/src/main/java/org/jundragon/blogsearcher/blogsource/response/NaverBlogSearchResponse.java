package org.jundragon.blogsearcher.blogsource.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Builder;
import org.jundragon.blogsearcher.core.blog.domain.BlogDocument;

@Builder
public record NaverBlogSearchResponse(String lastBuildDate, // 검색 결과를 생성한 시간
                                      Integer total, // 총 검색 결과 개수
                                      Integer start, // 검색 시작 위치
                                      Integer display, //한 번에 표시할 검색 결과 개수
                                      List<BlogItem> items // 개별 검색 결과
) {

    @Builder
    public record BlogItem(String title, // 블로그 포스트의 제목
                           String link, // 블로그 포스트의 URL
                           String description, // 블로그 포스트의 내용을 요약한 패시지 정보
                           @JsonProperty("bloggername") String bloggerName, // 블로그 포스트가 있는 블로그의 이름
                           @JsonProperty("bloggerlink") String bloggerLink, // 블로그 포스트가 있는 블로그의 주소
                           String postdate // 블로그 포스트가 작성된 날짜
    ) {

        public BlogDocument to() {
            return BlogDocument.builder()
                .title(title)
                .contents(description)
                .url(link)
                .blogName(bloggerName)
                .createdAt(LocalDateTime.of(
                    LocalDate.parse(postdate, DateTimeFormatter.BASIC_ISO_DATE),
                    LocalTime.MIDNIGHT))
                .build();
        }
    }
}
