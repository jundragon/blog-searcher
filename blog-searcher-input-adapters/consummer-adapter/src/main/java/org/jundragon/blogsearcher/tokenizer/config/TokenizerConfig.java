package org.jundragon.blogsearcher.tokenizer.config;

import org.jundragon.blogsearcher.tokenizer.KeywordTokenizer;
import org.jundragon.blogsearcher.tokenizer.impl.DefaultKeywordTokenizer;
import org.jundragon.blogsearcher.tokenizer.impl.KomoranKeywordTokenizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenizerConfig {

    @Bean
    @ConditionalOnProperty(prefix = "tokenizer", name = "engine", havingValue = "none")
    public KeywordTokenizer defaultKeywordTokenizer() {
        return new DefaultKeywordTokenizer();
    }

    @Bean
    @ConditionalOnProperty(prefix = "tokenizer", name = "engine", havingValue = "komoran")
    public KeywordTokenizer komoranKeywordTokenizer() {
        return new KomoranKeywordTokenizer();
    }
}
