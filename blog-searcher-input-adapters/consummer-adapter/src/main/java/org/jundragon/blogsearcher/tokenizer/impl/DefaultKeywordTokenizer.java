package org.jundragon.blogsearcher.tokenizer.impl;

import java.util.List;
import org.jundragon.blogsearcher.tokenizer.KeywordTokenizer;

public class DefaultKeywordTokenizer implements KeywordTokenizer {

    @Override
    public List<String> tokenize(String sentance) {
        return List.of(sentance);
    }
}
