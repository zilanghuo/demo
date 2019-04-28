package com.zilanghuo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laiwufa
 * @date 2019/4/28 0028 下午 4:26
 */
@Service
public class BService {

    @Autowired
    private CService cService;

    public void testB(){
        System.out.println("testB.....................");
        cService.testC();
    }
}
