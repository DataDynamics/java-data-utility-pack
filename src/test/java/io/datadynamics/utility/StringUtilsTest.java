package io.datadynamics.utility;

import io.datadynamics.utility.StringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void isBlank() { // 공백은 허용함
        Assert.assertTrue(StringUtils.isBlank("  "));
    }

    @Test
    void isNotBlank() {
        Assert.assertTrue(StringUtils.isNotBlank(" 1 "));
    }

    @Test
    void isEmpty() { // 공백도 허용하지 않음
        Assert.assertFalse(StringUtils.isEmpty("  "));
        Assert.assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    void isNotEmpty() {
        Assert.assertTrue(StringUtils.isNotEmpty("  "));
        Assert.assertTrue(StringUtils.isNotEmpty("12123123"));
    }

    @Test
    void truncate1() {
        Assert.assertEquals("hello", StringUtils.truncate("helloworld", 5));
        Assert.assertEquals("홍길", StringUtils.truncate("홍길동", 2));
    }

    @Test
    void truncate2() {
        Assert.assertEquals("안녕하세요. 홍...", StringUtils.truncate("안녕하세요. 홍길동입니다.", 8, "..."));
    }
}