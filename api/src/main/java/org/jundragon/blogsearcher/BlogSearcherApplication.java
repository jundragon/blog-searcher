package org.jundragon.blogsearcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlogSearcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSearcherApplication.class, args);
    }
}
