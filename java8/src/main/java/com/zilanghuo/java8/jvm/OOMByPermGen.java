package com.zilanghuo.java8.jvm;

import java.util.ArrayList;
import java.util.List;

/**-XX:PermSize=2M -XX:MaxPermSize=2M
 * PermGen space
 * @author laiwufa
 * @date 2019/4/3 0003 下午 2:45
 */
public class OOMByPermGen {

    public static void main(String[] args) {
        List<String> list = new ArrayList();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
            System.out.println(i);
        }

    }

}
