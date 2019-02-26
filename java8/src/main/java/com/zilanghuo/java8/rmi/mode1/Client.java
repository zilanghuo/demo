package com.zilanghuo.java8.rmi.mode1;

import java.rmi.Naming;

public class Client {

    public static void main(String[] args) throws Exception {
            UserHandler handler = (UserHandler) Naming.lookup("user");
            int  count = handler.getUserCount();
            String name = handler.getUserName(1);
            System.out.println("name: " + name);
            System.out.println("count: " + count);
            System.out.println("user: " + handler.getUserByName("lmy86263"));
    }
}
