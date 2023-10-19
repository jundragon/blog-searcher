package org.jundragon.blogsearcher.blogsource.factory;

import javax.validation.Valid;
import org.jundragon.blogsearcher.blogsource.request.BlogSearchRequest;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import reactor.core.publisher.Mono;

public interface BlogSourceOpenApiClient {

    Mono<Blog> searchBlogDocuments(@Valid BlogSearchRequest request);

}
