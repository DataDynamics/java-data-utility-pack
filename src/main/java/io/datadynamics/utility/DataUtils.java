package io.datadynamics.utility;

import com.google.common.base.Joiner;
import io.datadynamics.shaded.org.apache.commons.lang3.RandomStringUtils;
import io.datadynamics.shaded.org.apache.commons.lang3.RandomUtils;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class DataUtils {

    public static void generateSampleFile(String filename, int maxRow) throws Exception {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timestamp1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter timestamp2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        DateTimeFormatter timestamp3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        FileOutputStream fos = new FileOutputStream(filename);
        OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");

        writer.write("COL_INT,COL_FLOAT,COL_DOUBLE,COL_STRING,COL_DATE,COL_TIMESTAMP,COL_TIMESTAMP_MILLIS,COL_TIMESTAMP_MICROS\n");

        int i = 0;
        while (i < maxRow) {
            List<String> row = new ArrayList();
            row.add("" + RandomUtils.nextInt()); // COL_INT
            row.add("" + RandomUtils.nextLong()); // COL_FLOAT
            row.add("" + randomDouble(0.001, 9999999)); // COL_DOUBLE
            row.add(RandomStringUtils.random(10, true, true)); // COL_STRING
            row.add(toString(date)); // COL_DATE
            row.add(toString(timestamp1)); // COL_TIMESTAMP
            row.add(toString(timestamp2)); // COL_TIMESTAMP_MILLIS
            row.add(toString(timestamp3)); // COL_TIMESTAMP_MICROS

            String csv = Joiner.on(",").join(row) + "\n";
            writer.write(csv);

            i++;
        }

        writer.close();
        fos.close();
    }

    public static double randomDouble(double min, double max) {
        SplittableRandom sr = new SplittableRandom();
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return sr.nextDouble(min, Math.nextUp(max));
    }

    public static String toString(DateTimeFormatter formatter) {
        Timestamp timestamp = randomTimestamp();
        return toString(formatter, timestamp);
    }

    public static String toString(DateTimeFormatter formatter, Timestamp timestamp) {
        return formatter.format(timestamp.toLocalDateTime());
    }

    public static Timestamp randomTimestamp() {
        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long) (Math.random() * diff));
    }

    public static Timestamp randomTimestamp(String startDate, String endDate) {
        long offset = Timestamp.valueOf(startDate + " 00:00:00").getTime();
        long end = Timestamp.valueOf(endDate + " 23:59:59").getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long) (Math.random() * diff));
    }
}
