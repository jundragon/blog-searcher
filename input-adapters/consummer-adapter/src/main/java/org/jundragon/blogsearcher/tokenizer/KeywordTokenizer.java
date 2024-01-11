package org.jundragon.blogsearcher.tokenizer;

import java.util.List;

@FunctionalInterface
public interface KeywordTokenizer {

    List<String> tokenize(String sentance);
}
