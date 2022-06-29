package com.zilanghuo.hmac.test;


import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

/**
 * Created by laiwufa on 2022-02-18
 */
public class HmacUtil {


    public static void main(String[] args) throws Exception{
        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        String format = df.format(date);
        System.out.println(format);

        Date parse = df.parse(format);
        System.out.println(date.getTime());
        System.out.println(parse.getTime());
        System.out.println(df.format(parse));

    }

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
