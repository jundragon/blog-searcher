package org.jundragon.blogsearcher.core.blog.application.port.output.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.jundragon.blogsearcher.core.blog.domain.Blog;
import org.jundragon.blogsearcher.core.blog.domain.BlogDocument;
import org.jundragon.blogsearcher.core.blog.domain.Pagination;

@Builder
public record BlogResponse (List<BlogDocument> documents, Pagination pagination) {
    // 생성로직
    public static BlogResponse from(Blog blog) {
        return BlogResponse.builder()
            .documents(blog.getDocuments())
            .pagination(blog.getPagination())
            .build();
    }
}
