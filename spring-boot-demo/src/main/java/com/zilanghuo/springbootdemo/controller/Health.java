package com.zilanghuo.springbootdemo.controller;

import com.zilanghuo.springbootdemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class Health {

    private static final Logger log = LoggerFactory.getLogger(Health.class);

    @RequestMapping(value = "/health2/check")
    @ResponseBody
    public User check() {
        try {
            log.info("健康检查-----------------------------------");
            log.info("最大可用内存:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M\t");
            log.info("当前JVM空闲内存:" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M\t");
            log.info("当前JVM占用的内存总数:" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M\t");
            log.info("-----------------------------------");
            User user = new User();
            user.setAge(12);
            user.setId(2323L);
            user.setName("小红");
            return user;
        } catch (Exception e) {
            log.error("checkHealth error", e);
            return new User();
        }

    }

}
