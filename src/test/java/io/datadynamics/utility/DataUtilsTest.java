package io.datadynamics.utility;

import io.datadynamics.utility.DataUtils;
import org.junit.jupiter.api.Test;

public class DataUtilsTest {

    @Test
    void generateSampleFile() throws Exception {
        DataUtils.generateSampleFile("target/helloworld.csv", 100);
    }

}