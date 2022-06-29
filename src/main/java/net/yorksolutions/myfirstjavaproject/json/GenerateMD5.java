package net.yorksolutions.myfirstjavaproject.json;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateMD5 {
    public static String generateMD5(String to_be_md5) {
        String md5_sum = "";

        //If the provided String is null, then throw an Exception.
        if (to_be_md5 == null) {
            throw new RuntimeException("There is no string to calculate a MD5 hash from.");
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(to_be_md5.getBytes(StandardCharsets.UTF_8));
            StringBuilder collector = new StringBuilder();
            for (byte b : array) {
                collector.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            md5_sum = collector.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not find a MD5 instance: " + e.getMessage());
        }

        return md5_sum;
    }
}
