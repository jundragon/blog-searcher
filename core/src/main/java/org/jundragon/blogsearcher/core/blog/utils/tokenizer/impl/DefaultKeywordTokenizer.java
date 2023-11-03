package org.jundragon.blogsearcher.core.blog.utils.tokenizer.impl;

import java.util.List;
import org.jundragon.blogsearcher.core.blog.utils.tokenizer.KeywordTokenizer;

public class DefaultKeywordTokenizer implements KeywordTokenizer {

    @Override
    public List<String> tokenize(String sentance) {
        return List.of(sentance);
    }
}
