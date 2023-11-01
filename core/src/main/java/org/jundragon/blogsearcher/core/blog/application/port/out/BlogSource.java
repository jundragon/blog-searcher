package org.jundragon.blogsearcher.core.blog.application.port.out;

import org.jundragon.blogsearcher.core.blog.domain.Blog;
import reactor.core.publisher.Mono;

public interface BlogSource {

    Mono<Blog> searchBlogDocuments(BlogSourceRequest request);
}
