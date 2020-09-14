package com.zilanghuo.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author laiwufa
 * @date 2019/3/14 0014 下午 3:43
 */
public class Test {

    @org.junit.Test
    public void pattern(){
        String s = "2019-04-28 18:05:36.0";
        System.out.println(s.substring(0,19));
    }

    @org.junit.Test
    public void testThree() {
        String str = "/home/duyiping/tasklogs/121967_task-priority2.3_2020-06-29.log";
        String exe = "10.0.90.57:9090";
        System.out.println(exe.substring(0,exe.lastIndexOf(":")));
        System.out.println(str.substring(str.lastIndexOf("/") + 1));
        System.out.println(str.substring(str.length() - 14,str.length() - 4));

    }

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
