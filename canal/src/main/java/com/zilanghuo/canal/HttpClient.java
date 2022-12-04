package com.zilanghuo.canal;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by laiwufa on 2022-08-04
 */
public class HttpClient {

    public static void main(String[] args) throws Exception{

      /*  for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpUtil.get("http://10.208.14.33:8210/job/onlineCron?jobId=646");
                }
            }).start();
        }
        Thread.sleep(10000);*/

        sendJobSearch();
    }

    public static void sendJobSearch() throws Exception{

        Map<String,Object> map = new HashMap<>();
        map.put("isOnline",false);
        map.put("orgId","bgzt000004");
        //map.put("userCode","81090253");

        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpUtil.post("http://10.208.14.33:8210/job/jobSearchByName?pageNum=1&pageSize=10", JSON.toJSONString(map));
                }
            }).start();
        }
        Thread.sleep(100000);
    }
}
