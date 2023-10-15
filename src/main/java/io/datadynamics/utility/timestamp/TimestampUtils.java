package io.datadynamics.utility.timestamp;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampUtils {

    /**
     * 문자열 Timestamp 시간을 java.sql.Timestamp로 변환한다.
     *
     * @param value   문자열 Timestamp 시간
     * @param pattern Timestamp Pattern
     * @return java.sql.Timestamp
     */
    public static Timestamp parseStringTimestamp(String value, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(value, formatter);
        return Timestamp.valueOf(dateTime);
    }

    /**
     * java.sql.Timestamp를 문자열로 포맷팅한다.
     *
     * @param timestamp java.sql.Timestamp
     * @param pattern   Timestamp Pattern
     * @return 문자열 Timestamp 시간
     */
    public static String formatTimestamp(Timestamp timestamp, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(timestamp.toLocalDateTime());
    }

    public static LocalDateTime plusDays(LocalDateTime ldt, int add) {
        return ldt.plus(Duration.ofDays(add));
    }

    public static LocalDateTime plusHours(LocalDateTime ldt, int add) {
        return ldt.plus(Duration.ofHours(add));
    }

    public static LocalDateTime plusMinutes(LocalDateTime ldt, int add) {
        return ldt.plus(Duration.ofMinutes(add));
    }

    public static LocalDateTime plusSeconds(LocalDateTime ldt, int add) {
        return ldt.plus(Duration.ofSeconds(add));
    }

    public static Timestamp toTimestamp(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt);
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

}
