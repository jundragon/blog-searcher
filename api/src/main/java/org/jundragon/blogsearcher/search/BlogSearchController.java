package org.jundragon.blogsearcher.search;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jundragon.blogsearcher.common.api.ApiResponse;
import org.jundragon.blogsearcher.core.blog.application.port.out.BlogResponse;
import org.jundragon.blogsearcher.core.blog.application.service.BlogSearchService;
import org.jundragon.blogsearcher.search.request.BlogSearchRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class BlogSearchController {

    private final BlogSearchService blogSearchService;


    @GetMapping("/blogs")
    public ApiResponse<BlogResponse> search(@Valid BlogSearchRequest request) {

        BlogResponse response = blogSearchService.search(request.to());

        return ApiResponse.OK(response);
    }
}
