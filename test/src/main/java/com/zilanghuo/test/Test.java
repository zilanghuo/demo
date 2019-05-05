package com.zilanghuo.test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;

/**
 * @author laiwufa
 * @date 2019/3/14 0014 下午 3:43
 */
public class Test {

    public static void main(String[] args) throws Exception {

        TreeMap<BigDecimal, String> map = new TreeMap();
        map.put(BigDecimal.TEN, "1");
        map.put(BigDecimal.ZERO, "2");
        map.put(new BigDecimal(11), "3");
        map.put(new BigDecimal(9), "4");
        System.out.println(map.get(map.lastKey()));
    }

    static int test() {
        int i = 0;
        try {
            return i;
        } catch (Exception e) {
            return -1;
        } finally {
            i++;
        }
    }

    void getOne() {
        return;
    }

    static void a() {
    }

}
