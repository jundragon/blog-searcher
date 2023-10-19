package org.jundragon.blogsearcher.statistics;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.common.api.ApiResponse;
import org.jundragon.blogsearcher.core.blog.application.port.in.SearchTopPopularKeywordCommand;
import org.jundragon.blogsearcher.core.blog.application.service.BlogStatisticService;
import org.jundragon.blogsearcher.statistics.request.TopPopularKeywordRequest;
import org.jundragon.blogsearcher.statistics.response.SearchTopPopularKeywordResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/blogs/statistics")
@RestController
@RequiredArgsConstructor
public class BlogStatisticsController {

    private final BlogStatisticService blogStatisticService;

    @GetMapping("/popular")
    public ApiResponse<Object> searchTopPopularKeyword(@Valid TopPopularKeywordRequest request) {

        List<SearchTopPopularKeywordResponse> response = blogStatisticService
            .getTopPopularKeyword(
                SearchTopPopularKeywordCommand.builder()
                    .top(request.getTop()).build())
            .stream().map(SearchTopPopularKeywordResponse::from)
            .toList();

        return ApiResponse.OK(response);
    }
}
