package org.jundragon.blogsearcher.core.blog.utils;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TokenizeUtilsTest {

    @Test
    void 문장에서_단어를_가져올_수_있다() {
        // given
        String sut = "오늘하루 어떠셨나요? 식사는 하셨나요?";

        // when
        List<String> tokenize = TokenizeUtils.tokenize(sut);

        // then
        Assertions.assertEquals("오늘", tokenize.get(0));
        Assertions.assertEquals("하루", tokenize.get(1));
        Assertions.assertEquals("식사", tokenize.get(2));
    }

}