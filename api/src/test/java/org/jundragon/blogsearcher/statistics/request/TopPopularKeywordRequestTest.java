package org.jundragon.blogsearcher.statistics.request;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TopPopularKeywordRequestTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void close() {
        factory.close();
    }

    @Test
    void 인기검색어_최대_10개_가능() {
        // given
        TopPopularKeywordRequest request = TopPopularKeywordRequest.builder()
            .top(10L)
            .build();

        // when
        Set<ConstraintViolation<TopPopularKeywordRequest>> violations = validator.validate(request);

        // then
        Assertions.assertTrue(violations.isEmpty());

    }

    @Test
    void 인기검색어_최대_10개이상_유효성검사() {
        // given
        TopPopularKeywordRequest request = TopPopularKeywordRequest.builder()
            .top(100L)
            .build();

        // when
        Set<ConstraintViolation<TopPopularKeywordRequest>> violations = validator.validate(request);

        // then
        Assertions.assertFalse(violations.isEmpty());

    }

    @Test
    void SearchTopPopularKeywordRequest_에_데이터가_없을_경우_기본값으로_출력되어야_한다() {
        // given
        TopPopularKeywordRequest request = TopPopularKeywordRequest.builder()
            .top(null)
            .build();

        // when
        Set<ConstraintViolation<TopPopularKeywordRequest>> violations = validator.validate(request);

        // then
        Assertions.assertTrue(violations.isEmpty());
        Assertions.assertEquals(10L, request.getTop());
    }

}