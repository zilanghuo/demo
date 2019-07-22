package com.zilanghuo.test;

import cn.hutool.json.JSONUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author laiwufa
 * @date 2019/3/14 0014 下午 3:43
 */
public class Test {

    @org.junit.Test
    public  void testTwo() throws Exception{
        Date dateline = new Date();
        Thread.sleep(1000);
        if(new Date().after(dateline)){		   //2019-05版本
            System.out.println("1");
        }else if(Objects.equals("on","on")){ //2019-03版本
            System.out.println("2");
        }else{
            System.out.println("3");
        }
    }

    @org.junit.Test
    public void testOne() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(1, 1);
        System.out.println(JSONUtil.toJsonStr(list));

    }

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
