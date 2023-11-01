package org.jundragon.blogsearcher.controller.search.request;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.jundragon.blogsearcher.core.blog.application.port.input.BlogSearchCommand;
import org.jundragon.blogsearcher.core.blog.domain.BlogSearchSortType;

@Builder
@Getter
public class BlogSearchRequest {

    @NotBlank(message = "키워드는 필수입니다.")
    private final String keyword;
    private final BlogSearchSortType sort;

    public BlogSearchRequest(String keyword, BlogSearchSortType sort) {
        this.keyword = keyword;
        this.sort = Objects.isNull(sort) ? BlogSearchSortType.ACCURACY : sort;
    }

    public BlogSearchCommand to() {
        return BlogSearchCommand.builder()
            .keyword(keyword)
            .sort(sort)
            .build();
    }
}
