package com.liyan.store.utils;

import java.security.MessageDigest;

public class MD5Helper {
    /**
     * 加密-32位小写
     */
    public  String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    /**
     *   加密-16位小写

     */
    public  String encrypt16(String encryptStr) {
        return encrypt32(encryptStr).substring(8, 24);
    }


}
