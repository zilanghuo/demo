package com.zilanghuo.java8.rmi.mode1;

import java.rmi.Naming;

public class ServerPublish {

    public static void main(String[] args) {
        UserHandler userHandler = null;
        try {
            userHandler = new UserHandlerImpl();
            Naming.rebind("user", userHandler);
            System.out.println(" rmi server is ready ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
