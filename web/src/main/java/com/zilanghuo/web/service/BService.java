package com.zilanghuo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laiwufa
 * @date 2019/4/28 0028 下午 4:26
 */
public class BService {
    private CService cService;

   /* public BService(CService cService) {
        this.cService = cService;
    }*/

    public void testB(){
        System.out.println("testB.....................");
    }

    public void setcService(CService cService) {
        this.cService = cService;
    }
}
