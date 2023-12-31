package org.jundragon.blogsearcher.controller.statistics.request;

import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TopPopularKeywordRequest {

    @Min(1)
    @Max(10)
    private Long top;

    @Builder
    private TopPopularKeywordRequest(Long top) {
        this.top = Objects.isNull(top) ? 10L : top;
    }
}
