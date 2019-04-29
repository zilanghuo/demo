package com.zilanghuo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laiwufa
 * @date 2019/4/28 0028 下午 4:26
 */
public class CService {

    private AService aService;

/*
    public CService(AService aService) {
        this.aService = aService;
    }
*/

    public void testC(){
        System.out.println("testC.....................");
    }

    public void setaService(AService aService) {
        this.aService = aService;
    }
}
