package com.zilanghuo.sofademo;

import com.alipay.sofa.registry.client.api.RegistryClientConfig;
import com.alipay.sofa.registry.client.provider.DefaultRegistryClient;
import com.alipay.sofa.registry.client.provider.DefaultRegistryClientConfigBuilder;

/**
 * Created by laiwufa on 2019-11-12
 */
public class SofaDemo {

    public static void main(String[] args) throws Exception {
        RegistryClientConfig config= DefaultRegistryClientConfigBuilder.start().setRegistryEndpoint("127.0.0.1")
                .setRegistryEndpointPort(9602).build();
        DefaultRegistryClient registryClient=new DefaultRegistryClient(config);
        registryClient.init();

    }
}
