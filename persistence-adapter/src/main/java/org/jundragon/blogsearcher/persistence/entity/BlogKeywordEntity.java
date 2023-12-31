package org.jundragon.blogsearcher.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jundragon.blogsearcher.core.blog.domain.BlogKeyword;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "blog_keyword",
    indexes = @Index(columnList = "keyword"),
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_blog_keyword",
            columnNames = {"keyword"}
        )
    }
)
@Entity
public class BlogKeywordEntity extends BaseEntity {

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
