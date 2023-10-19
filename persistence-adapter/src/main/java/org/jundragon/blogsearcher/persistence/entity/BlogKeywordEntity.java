package org.jundragon.blogsearcher.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jundragon.blogsearcher.core.blog.domain.BlogKeyword;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BlogKeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "count")
    private long count;

    @Builder
    private BlogKeywordEntity(Long id, String keyword, long count) {
        this.id = id;
        this.keyword = keyword;
        this.count = count;
    }

    public static BlogKeywordEntity from(BlogKeyword blogKeyword) {
        return BlogKeywordEntity.builder()
            .id(blogKeyword.getId())
            .keyword(blogKeyword.getKeyword())
            .count(blogKeyword.getCount())
            .build();
    }

    public BlogKeyword to() {
        return BlogKeyword.builder()
            .id(this.id)
            .keyword(this.keyword)
            .count(this.count)
            .build();
    }
}
