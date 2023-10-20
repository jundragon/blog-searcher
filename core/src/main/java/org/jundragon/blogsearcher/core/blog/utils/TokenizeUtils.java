package org.jundragon.blogsearcher.core.blog.utils;

import java.util.List;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.Getter;

public abstract class TokenizeUtils {

    private final static Komoran engine;

    static {
        engine = new Komoran(DEFAULT_MODEL.LIGHT);
    }

    public static List<String> tokenize(String sentance) {
        return engine.analyze(sentance).getTokenList().stream()
            .filter(token -> isNoun(token.getPos()))
            .map(Token::getMorph).toList();
    }

    private static boolean isNoun(String str) {
        if (KeywordType.COMMON_NOUN.getCode().equals(str)) {
            return true;
        }
        if (KeywordType.PROPER_NOUN.getCode().equals(str)) {
            return true;
        }
        if (KeywordType.PRO_NOUN.getCode().equals(str)) {
            return true;
        }

        return false;
    }

    /**
     * 품사표 https://docs.komoran.kr/firststep/postypes.html
     */
    private enum KeywordType {

        COMMON_NOUN("일반명사", "NNG"),
        PROPER_NOUN("고유명사", "NNP"),
        PRO_NOUN("대명사", "NP"),
        ;

        KeywordType(String name, String code) {
            this.name = name;
            this.code = code;
        }

        private String name;

        @Getter
        private String code;
    }
}
