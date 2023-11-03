package org.jundragon.blogsearcher.core.blog.utils.tokenizer;

import java.util.List;

@FunctionalInterface
public interface KeywordTokenizer {
    List<String> tokenize(String sentance);
}
