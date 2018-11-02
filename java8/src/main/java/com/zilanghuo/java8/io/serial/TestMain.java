package com.zilanghuo.java8.io.serial;

import org.junit.Test;

import java.io.File;

/**
 * @author laiwufa
 * @date 2018/11/2
 * use:
 */
public class TestMain {

    @Test
    public void nioInputTest() {
        File file = new File("f://temp.txt");
        InPutUtil.fileInputRead(file);
        NioInputUtil.randomAccessInput(file);
    }

    public static void main(String[] args) {
        People people = new People();
        people.setAge(12);
        people.setName("xiaohong");
        System.out.println("序列化前：" + people);
        File file = new File("f://temp.txt");
        OutPutUtil.write(file, people);
        People readPeople = (People) InPutUtil.read(file);
        System.out.println("序列化后：" + readPeople);
    }

}
