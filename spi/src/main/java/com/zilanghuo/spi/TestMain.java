package com.zilanghuo.spi;

import java.util.ServiceLoader;

public class TestMain {

    public static void main(String[] args) throws Exception {
        ServiceLoader<People> loader = ServiceLoader.load(People.class);
        for (People people : loader) {
            people.sayHello();
        }

    }

}
