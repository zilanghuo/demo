package com.zilang.web;

import com.zilanghuo.web.service.AService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author laiwufa
 * @date 2019/4/28 0028 下午 5:50
 */
public class testCycleByConstruct {

    public static void main(String[] args) {
        // ApplicationContext context = new ClassPathXmlApplicationContext("classpath:testCycleByConstruct.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:testCycleBySet.xml");
        AService aService = (AService) context.getBean("aService");
        aService.testA();
    }

}
