package com.zilanghuo.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author laiwufa
 * @date 2019/4/10 0010 下午 3:22
 */
public class FanXingTest {

    public static void main(String[] args) {
        testInteger();
    }

    public static void testOne(){
        if (true){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }

    /**
     * 测试自动装箱
     */
    public static void testInteger(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d); // true
        System.out.println(e == f); // false
        System.out.println(c == (a + b)); // true
        System.out.println(c.equals(a + b)); // true
        System.out.println(g == (a + b)); // true
        System.out.println(g.equals(a + b));// false
    }

    /**
     * 测试泛型擦除
     */
    public static void testFanXing(){
        Map<String,String> map = new HashMap<String,String>(2);
        map.put("121","3123");
        map.put("122","223");
        System.out.println(map.get("121"));
        System.out.println(map.get("122"));
    }

}
