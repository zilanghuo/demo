package com.zilanghuo.springcloud.productone;

import com.zilanghuo.springcloud.productone.service.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by laiwufa on 2019-11-06
 */
@RestController
@Scope("singleton")
public class HealthController {

    @Autowired
    private DiscoveryService discoveryService;

    //http://localhost:8111/health 获取配置信息
    @RequestMapping("/health")
    public void health(){
        System.out.println("-------------------"+discoveryService.getConfigServiceInstances());
    }
}
