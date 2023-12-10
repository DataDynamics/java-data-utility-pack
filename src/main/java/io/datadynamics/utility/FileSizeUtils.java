package io.datadynamics.utility;


import java.io.File;

/**
 * 로컬 파일 시스템의 지정한 파일의 크기를 확인하는 유틸리티 클래스.
 */
public class FileSizeUtils {

    /**
     * 1K Bytes
     */
    public static long ONE_KILO_BYTES = 1 * 1024;

    /**
     * 10K Bytes
     */
    public static long TEN_KILO_BYTES = 10 * 1024;

    /**
     * 100K Bytes
     */
    public static long ONE_HUNDRED_KILO_BYTES = 100 * 1024;

    /**
     * 1M Bytes
     */
    public static long ONE_MEGA_BYTES = 1 * 1024 * 1024;

    /**
     * 10M Bytes
     */
    public static long TEN_MEGA_BYTES = 10 * 1024 * 1024;

    /**
     * 100M Bytes
     */
    public static long ONE_HUNDRED_MEGA_BYTES = 100 * 1024 * 1024;

    private static final long KB = ONE_KILO_BYTES;
    private static final long MB = 1024 * KB;
    private static final long GB = 1024 * MB;
    private static final long TB = 1024 * GB;

    /**
     * 지정한 경로의 파일의 1K 바이트보다 작은지 확인한다.
     *
     * @param path 로컬 파일의 절대 경로
     * @return 1M 보다 큰 경우 <tt>false</tt>, 작은 경우 <tt>true</tt>
     */
    public static boolean lessThan1KBytes(String path) {
        return lessThanSpecificSize(ONE_KILO_BYTES, path);
    }

    /**
     * 지정한 경로의 파일의 10K 바이트보다 작은지 확인한다.
     *
     * @param path 로컬 파일의 절대 경로
     * @return 1M 보다 큰 경우 <tt>false</tt>, 작은 경우 <tt>true</tt>
     */
    public static boolean lessThan10KBytes(String path) {
        return lessThanSpecificSize(TEN_KILO_BYTES, path);
    }

    /**
     * 지정한 경로의 파일의 100K 바이트보다 작은지 확인한다.
     *
     * @param path 로컬 파일의 절대 경로
     * @return 1M 보다 큰 경우 <tt>false</tt>, 작은 경우 <tt>true</tt>
     */
    public static boolean lessThan100KBytes(String path) {
        return lessThanSpecificSize(ONE_HUNDRED_KILO_BYTES, path);
    }

    /**
     * 지정한 경로의 파일의 1M 바이트보다 작은지 확인한다.
     *
     * @param path 로컬 파일의 절대 경로
     * @return 1M 보다 큰 경우 <tt>false</tt>, 작은 경우 <tt>true</tt>
     */
    public static boolean lessThan1MBytes(String path) {
        return lessThanSpecificSize(ONE_MEGA_BYTES, path);
    }

    /**
     * 지정한 경로의 파일의 100K 바이트보다 작은지 확인한다.
     *
     * @param path 로컬 파일의 절대 경로
     * @return 1M 보다 큰 경우 <tt>false</tt>, 작은 경우 <tt>true</tt>
     */
    public static boolean lessThan10MBytes(String path) {
        return lessThanSpecificSize(TEN_MEGA_BYTES, path);
    }

    /**
     * 지정한 경로의 파일의 100K 바이트보다 작은지 확인한다.
     *
     * @param path 로컬 파일의 절대 경로
     * @return 1M 보다 큰 경우 <tt>false</tt>, 작은 경우 <tt>true</tt>
     */
    public static boolean lessThan100MBytes(String path) {
        return lessThanSpecificSize(ONE_HUNDRED_MEGA_BYTES, path);
    }

    /**
     * 지정한 경로의 파일의 1M 바이트보다 작은지 확인한다.
     *
     * @param path 로컬 파일의 절대 경로
     * @return 1M 보다 큰 경우 <tt>false</tt>, 작은 경우 <tt>true</tt>
     */
    public static boolean lessThanSpecificSize(long size, String path) {
        return new File(path).length() < size;
    }

    public static String humanReadable(float bytes) {
        if (bytes > TB) {
            return String.format("%.03f TB", bytes / TB);
        } else if (bytes > GB) {
            return String.format("%.03f GB", bytes / GB);
        } else if (bytes > MB) {
            return String.format("%.03f MB", bytes / MB);
        } else if (bytes > KB) {
            return String.format("%.03f kB", bytes / KB);
        } else {
            return String.format("%.02f B", bytes);
        }
    }

    public static String humanReadable(long bytes) {
        if (bytes > TB) {
            return String.format("%.03f TB", ((float) bytes) / TB);
        } else if (bytes > GB) {
            return String.format("%.03f GB", ((float) bytes) / GB);
        } else if (bytes > MB) {
            return String.format("%.03f MB", ((float) bytes) / MB);
        } else if (bytes > KB) {
            return String.format("%.03f kB", ((float) bytes) / KB);
        } else {
            return String.format("%d B", bytes);
        }
    }

}