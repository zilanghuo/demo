package com.zilanghuo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laiwufa
 * @date 2019/4/28 0028 下午 4:26
 */
public class AService {

    public BService bService;

  /*  public AService(BService bService) {
        this.bService = bService;
    }*/

    public void testA(){
        System.out.println("testA.....................");
    }

    public void setbService(BService bService) {
        this.bService = bService;
    }
}
