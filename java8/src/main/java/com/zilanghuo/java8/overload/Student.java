package com.zilanghuo.java8.overload;

/**
 * @author laiwufa
 * @date 2019/3/20 0020 上午 9:33
 */
public class Student extends People {

    static {
        System.out.println("student static code");
    }

    Student(){
        System.out.println("student construct");
    }

    public static void main(String[] args) {
        Student student = new Student();
    }
}
