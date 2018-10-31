package com.zilanghuo.java8.jvm;

import sun.misc.Launcher;

import java.net.URL;
import java.util.HashMap;

/**
 * @author laiwufa
 * @date 2018/10/30
 * use: 《码出高效》 120p
 */
public class TestClassLoader {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (java.net.URL url:urLs){
            System.out.println(url.toExternalForm());
        }
    }

}
