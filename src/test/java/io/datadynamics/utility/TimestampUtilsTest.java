package io.datadynamics.utility;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class TimestampUtilsTest {

    @Test
    public void parseAndFormat() {
        Timestamp t1 = TimestampUtils.parseStringTimestamp("2011-11-11 11:11:11", "yyyy-MM-dd HH:mm:ss");
        Assert.assertEquals("2011-11-11 11:11:11", TimestampUtils.formatTimestamp(t1, "yyyy-MM-dd HH:mm:ss"));

        Timestamp t2 = TimestampUtils.parseStringTimestamp("2011-11-11 11:11:11.111", "yyyy-MM-dd HH:mm:ss.SSS");
        Assert.assertEquals("2011-11-11 11:11:11.111", TimestampUtils.formatTimestamp(t2, "yyyy-MM-dd HH:mm:ss.SSS"));

        Timestamp t3 = TimestampUtils.parseStringTimestamp("2011-11-11 11:11:11.111111", "yyyy-MM-dd HH:mm:ss.SSSSSS");
        Assert.assertEquals("2011-11-11 11:11:11.111111", TimestampUtils.formatTimestamp(t3, "yyyy-MM-dd HH:mm:ss.SSSSSS"));

        Timestamp t4 = TimestampUtils.parseStringTimestamp("2011-11-11 11:11:11.111111111", "yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
        Assert.assertEquals("2011-11-11 11:11:11.111111111", TimestampUtils.formatTimestamp(t4, "yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
    }

}