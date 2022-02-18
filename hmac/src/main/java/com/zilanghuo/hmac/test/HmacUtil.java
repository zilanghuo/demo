package com.zilanghuo.hmac.test;


import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by laiwufa on 2022-02-18
 */
public class HmacUtil {


    public static String HmacSHA256(String data, String key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            String result = (new BASE64Encoder()).encode(rawHmac);
            return result;
        } catch (Exception var6) {
            throw new RuntimeException("Failed to generate HMAC : " + var6.getMessage());
        }
    }
}
