package com.zilanghuo.lcbtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiwufa
 * @date 2019/3/26 0026 下午 5:27
 */
@RestController
@Scope("singleton")
@Slf4j
public class Health {

    @Autowired
    private ReadService readService;

    @RequestMapping(value = "/health/check")
    @ResponseBody
    public String check() {
        try {
            log.info("健康检查-----------------------------------");
            log.info("最大可用内存:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M\t");
            log.info("当前JVM空闲内存:" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M\t");
            log.info("当前JVM占用的内存总数:" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M\t");
            log.info("-----------------------------------");
            return "OK";
        } catch (Exception e) {
            log.error("checkHealth error", e);
            return "FAIL";
        }

    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() {
        for (int i = 0; i < 10; i++) {
            readService.write();
        }
        return "ok";
    }

}
