package com.zilanghuo.web;

import com.zilanghuo.web.service.AService;
import com.zilanghuo.web.service.BService;
import com.zilanghuo.web.service.CService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiwufa
 * @date 2019/1/25
 */
@RestController
@Scope("singleton")
public class HealthController {

    @Autowired
    private AService aService;

    @Autowired
    private BService bService;

    @Autowired
    private CService cService;


    @RequestMapping(value = "/health/check")
    @ResponseBody
    public String check() {
        try {
            System.out.println(aService.toString());
            System.out.println(bService.toString());
            System.out.println(cService.toString());
            return "OK";
        } catch (Exception e) {
            return "FAIL";
        }

    }

}
