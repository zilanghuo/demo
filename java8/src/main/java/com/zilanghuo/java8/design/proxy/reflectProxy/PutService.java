package com.zilanghuo.java8.design.proxy.reflectProxy;


/**
 * @author laiwufa
 * @date 2018/9/23
 * use:
 */
public class PutService implements IPutService {

    public PutService(String a){
        System.out.println(a);
    }

    public PutService(){
    }

    @Override
    public void get() {
        System.out.println("real,get");
    }

    @Override
    public void put() {
        System.out.println("real,put");
    }
}
