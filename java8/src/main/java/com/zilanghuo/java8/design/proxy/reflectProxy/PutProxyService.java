package com.zilanghuo.java8.design.proxy.reflectProxy;


/**
 * @author laiwufa
 * @date 2018/9/23
 * use:
 */
public class PutProxyService implements IPutService {

    private IPutService putService;

    PutProxyService(IPutService putService){
        this.putService = putService;
    }

    @Override
    public void get() {
        before();
        putService.get();
        after();
    }

    @Override
    public void put() {
        before();
        putService.put();
        after();
    }

    private void before(){
        System.out.println("before----");
    }

    private void after(){
        System.out.println("after-----");
    }
}
