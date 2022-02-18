package com.zilanghuo.hmac;

/**
 * Created by laiwufa on 2022-02-18
 */
public class ServerMain {

    public static void main(String[] args) {
        // 时间认证，header 有date
        /**
         * Fri, 18 Feb 2022 07:19:03 GMT
         * SHA-256=bdatd+eAruFF59/QbMrwgIOUWgbwA48zzXmAqtEWrjo=
         * hmac username="clientId", algorithm="hmac-sha256", headers="date request-line digest", signature="h4dPMgQWofg7Fc7+JBp3rawxn5vPVrltpKUyM6lP0Dg="

         Map<String, String> map = new HashMap<>();
         map.put("username", "用户名");
         map.put("password", "密码");


         1、date 时间戳通过header 获取，判断时间戳
         2、将时间戳传入到明文，进行加密
         */



        // 参数认证

    }
}
