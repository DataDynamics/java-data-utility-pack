package io.datadynamics.utility;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {

    public static String encrypt(String key1, String key2, String value) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(key2.getBytes("UTF-8"));

        SecretKeySpec skeySpec = new SecretKeySpec(key1.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(value.getBytes());

        return Base64.encodeBase64String(encrypted);
    }

    public static String decrypt(String key1, String key2, String encrypted) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(key2.getBytes("UTF-8"));

        SecretKeySpec skeySpec = new SecretKeySpec(key1.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

        return new String(original);
    }
}

