package org.cdut.tzg.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author anlan
 * @date 2019/6/27 16:35
 */
public class MD5Utils {
    /**
     * MD5加密
     */
    public static String signByMd5(String... str) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        for (String temp : str) {
            md.update(temp.getBytes());
        }
        return String.format("%032x", new BigInteger(1, md.digest()));
    }
}
