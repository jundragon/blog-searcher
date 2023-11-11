package org.jundragon.blogsearcher.response;

import org.jundragon.blogsearcher.error.impl.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ApiResponseTest {

    @Test
    void SUCCESS_표준응답을_만들_수_있다() {
        // given
        String body = "응답데이터";

        // when
        ApiResponse<Object> actual = ApiResponse.OK(body);

        // then
        Assertions.assertNotNull(actual.body());
        Assertions.assertEquals("200", actual.result().code());
        Assertions.assertEquals("성공", actual.result().description());
        Assertions.assertEquals("응답데이터", actual.body());
    }

    @Test
    void ERROR_표준응답을_만들_수_있다() {
        // given
        // when
        ApiResponse<Object> actual = ApiResponse.ERROR(ErrorCode.BAD_REQUEST);

        // then
        Assertions.assertNull(actual.body());
        Assertions.assertEquals("400", actual.result().code());
    }

    @Test
    void ERROR_WITH_DESCRIOTION_표준응답을_만들_수_있다() {
        // given
        // when
        ApiResponse<Object> actual = ApiResponse.ERROR(ErrorCode.BAD_REQUEST, "설명추가");

        // then
        Assertions.assertNull(actual.body());
        Assertions.assertEquals("400", actual.result().code());
        Assertions.assertEquals("설명추가", actual.result().description());
    }

}