package org.jundragon.blogsearcher.search.request;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.jundragon.blogsearcher.core.blog.application.port.in.BlogSearchCommand;
import org.jundragon.blogsearcher.core.blog.domain.BlogSearchSortType;

@Builder
@Getter
public class BlogSearchRequest {

    @NotBlank(message = "키워드는 필수입니다.")
    private final String keyword;
    private final BlogSearchSortType sortType;

    public BlogSearchRequest(String keyword, BlogSearchSortType sortType) {
        this.keyword = keyword;
        this.sortType = Objects.isNull(sortType) ? BlogSearchSortType.ACCURACY : sortType;
    }

    public BlogSearchCommand to() {
        return BlogSearchCommand.builder()
            .keyword(keyword)
            .sortType(sortType)
            .build();
    }
}
