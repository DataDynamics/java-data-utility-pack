package io.datadynamics.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLEncoderUtil {

    public static String encodeURIParam(String s) throws UnsupportedEncodingException {

        String ret = null;

        try {
            ret = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw e;
        }

        return ret;
    }

}