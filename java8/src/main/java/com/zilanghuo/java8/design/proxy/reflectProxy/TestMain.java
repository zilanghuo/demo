package com.zilanghuo.java8.design.proxy.reflectProxy;


import java.lang.reflect.Constructor;

/**
 * @author laiwufa
 * @date 2018/9/23
 * use:
 */
public class TestMain {

    public static void main(String[] args) throws Exception{
        Class<IPutService> classz = (Class<IPutService>)Class.forName("com.zilanghuo.java8.design.proxy.reflectProxy.PutService");
        System.out.println(classz.getPackage().getName());
        Constructor<IPutService> con = classz.getConstructor();
        IPutService putService =  con.newInstance();

        Constructor<IPutService> constructor = classz.getConstructor(String.class);
        constructor.newInstance("23123");

    }
}
