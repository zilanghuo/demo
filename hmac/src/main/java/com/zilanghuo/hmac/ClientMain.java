package com.zilanghuo.hmac;

import com.alibaba.fastjson.JSON;
import com.cas.starter.configuration.SignatureUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by laiwufa on 2022-02-18
 */
public class ClientMain {


    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.251.129.24:8081/qinqiong/open/12321";
        //body参数，用map或者bean
        Map<String, String> map = new HashMap<>();
        map.put("username", "用户名");
        map.put("password", "密码");
        //header封装
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        Date date = new Date();
        String headerValue = SignatureUtils.toGMTString(date);
        String headerValue1 = SignatureUtils.generateDigestString(map);
        String headerValue2 = SignatureUtils.generateAuthorization(HttpMethod.POST, JSON.toJSONString(map), "HTTP/1.1", url, "clientId", "clientSecret222",date);

        header.set("Date", headerValue);
        header.set("Digest", headerValue1);
        header.set("Authorization", headerValue2);

        System.out.println(headerValue);
        System.out.println(headerValue1);
        System.out.println(headerValue2);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(map, header);
        //post请求
        String resKongDTO = restTemplate.postForObject(url, httpEntity, String.class);
        System.out.println(resKongDTO);
    }
}
