package com.zilanghuo.springcloud.productone.service;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoveryService {

  @Autowired
  private  EurekaClient eurekaClient;

  public List<InstanceInfo> getConfigServiceInstances() {
    Application application = eurekaClient.getApplication("product-server");
    return application.getInstances();
  }

}
