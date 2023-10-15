package io.datadynamics.utility;

public abstract class NativeDetector {

    // See https://github.com/oracle/graal/blob/master/sdk/src/org.graalvm.nativeimage/src/org/graalvm/nativeimage/ImageInfo.java
    private static final boolean imageCode = (System.getProperty("org.graalvm.nativeimage.imagecode") != null);

    public static boolean inNativeImage() {
        return imageCode;
    }
}
