package com.zilanghuo.test;

/**
 * @author laiwufa
 * @date 2019/4/18 0018 下午 4:11
 */
public class TestInit extends Father {
    static {
        System.out.println("init static"); //4
    }
    {
        System.out.println("init not static");
    }

    TestInit(){
        System.out.println("init construct");//5
    }

    public static void testFather(){
        System.out.println("init testFather");//6
    }

    public static void main(String[] args) {
        TestInit init = new TestInit();

    }
}

class Father{
    static {
        System.out.println("father static"); //1
    }
    {
        System.out.println("father not static");
    }

    Father(){
        System.out.println("father construct");//2
    }
    public static void testFather(){
        System.out.println("father testFather");//3
    }
}