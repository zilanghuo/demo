package com.zilanghuo.java8.io.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author laiwufa
 * @date 2019/3/20 0020 上午 9:46
 */
public class Test {

    public static void main(String[] args) throws Exception {

    }

    private static void inputTest() throws IOException {
        File file = new File("E:/json.txt");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] readbyte = new byte[1024];
        int offset = inputStream.read(readbyte);
        while (offset != -1) {
           // System.out.print(new String(readbyte));
            for (int i = 0; i < offset; i++) {
                System.out.print((char) readbyte[i]);
            }
            offset = inputStream.read(readbyte);
            System.out.println("----------offset:" + offset);
        }
    }

}
