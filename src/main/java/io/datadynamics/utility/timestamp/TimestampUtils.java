package io.datadynamics.utility.timestamp;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class TimestampUtils {

    /**
     * 기본 날짜 변환 포맷
     */
    public static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 매월의 일수
     */
    public static int DAYS_OF_MONTH[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

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

    /**
     * 현재 시간을 기준으로 "<tt>YYYYMMDDHH</tt>" 형식의 타임 스탬프를 생성한다.
     *
     * @return "<tt>YYYYMMDDHH</tt>" 형식의 문자열 타임 스탬프
     */
    public String getTomorrow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(plusDays(now, 1));
    }

    /**
     * 현재 시간을 기준으로 "<tt>YYYYMMDD</tt>" 형식의 어제 날짜를 생성한다.
     *
     * @return "<tt>YYYYMMDD</tt>" 형식의 문자열 타임 스탬프
     */
    public String getYesterday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(plusDays(now, -1));

    }

    /**
     * 현재 시간을 기준으로 "<tt>YYYYMMDD</tt>" 형식의 엊그제 날짜를 생성한다.
     *
     * @return "<tt>YYYYMMDD</tt>" 형식의 문자열 타임 스탬프
     */
    public String getADayBeforeYesterday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(plusDays(now, -2));
    }

    /**
     * 현재 시간을 기준으로 "<tt>YYYYMMDD</tt>" 형식의 엊그제 날짜를 생성한다.
     *
     * @return "<tt>YYYYMMDD</tt>" 형식의 문자열 타임 스탬프
     */
    public String getToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

    /**
     * 현재 날짜와 시각을 "yyyyMMddhhmmss" 형태로 변환한다.
     * <p>
     * <pre>
     * String today = getCurrentDateTime();
     * </pre>
     * </p>
     *
     * @return yyyyMMddhhmmss 형식의 문자열 날짜
     */
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        return formatter.format(now);
    }

    /**
     * 시작 시간과 종료 시간의 차이를 포맷팅한다.
     *
     * @param end   종료 시간
     * @param start 시작 시간
     * @return "H:M:S" 형식의 시간
     */
    public static String formatDiffTime(Date end, Date start) {
        long timeDiff = end.getTime() - start.getTime();
        return formatTime(timeDiff);
    }

    /**
     * 초단위 시간을 "H:M:S" 형식으로 포맷팅한다.
     *
     * @param diffLongTime 시간
     * @return "H:M:S" 형식의 시간
     */
    public static String formatTime(long diffLongTime) {
        StringBuffer buf = new StringBuffer();
        long hours = diffLongTime / (60 * 60 * 1000);
        long rem = (diffLongTime % (60 * 60 * 1000));
        long minutes = rem / (60 * 1000);
        rem = rem % (60 * 1000);
        long seconds = rem / 1000;

        if (hours != 0) {
            buf.append(hours);
            buf.append("시간 ");
        }
        if (minutes != 0) {
            buf.append(minutes);
            buf.append("분 ");
        }
        // 차이가 없다면 0을 반환한다.
        buf.append(seconds);
        buf.append("초");
        return buf.toString();
    }

    /**
     * 두 날짜가 같은 날짜인지 확인한다. 이 메소드는 시간은 무시한다.
     *
     * @param date1 비교할 첫번째 날짜
     * @param date2 비교할 두번째 날짜
     * @return 날짝 같으면 <tt>true</tt>
     * @throws IllegalArgumentException 날짜가 <tt>null인 경우</tt>
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return isSameDay(toCalendar(date1), toCalendar(date2));
    }

    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        Objects.requireNonNull(cal1, "cal1");
        Objects.requireNonNull(cal2, "cal2");
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static Calendar toCalendar(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(Objects.requireNonNull(date, "date"));
        return c;
    }
}
