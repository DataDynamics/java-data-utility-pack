package io.datadynamics.utility.util;

import org.junit.jupiter.api.Test;

public class DataUtilsTest {

    @Test
    void generateSampleFile() throws Exception {
        DataUtils.generateSampleFile("target/helloworld.csv", 100);
    }

}