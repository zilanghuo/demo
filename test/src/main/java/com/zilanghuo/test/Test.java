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
        String str = ".*/notice.*";
        String request = "/notice/get/all";
        boolean isMatch = Pattern.matches(str,request);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
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

    public static void main(String[] args)  {
        try {
            String cmd = String.format("/Users/admin/Downloads/kettle/data-integration/kitchen.sh -logfile=/Users/admin/Desktop/120681_test-kettle.log -file=/Users/admin/Desktop/yarn_job_param_test.kjb -level 'Debug' -param:instId=\"181608 00\"");
            String cmd2 = String.format("/bin/bash -c /Users/admin/Downloads/kettle/data-integration/kitchen.sh -file=/Users/admin/Desktop/yarn_job_param_test.kjb -logfile=/Users/admin/Desktop/120681_test-kettle.log -level 'Debug' -param:instId=181608");
            String cmd3 = "/bin/sh /Users/admin/Desktop/test.sh ";


            System.out.println(cmd);
            //ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c",killCommand);
            //Process process = pb.start();
            //int errCode = process.waitFor();
            //System.out.println(errCode);
            //String[] aa = new String[]{"/bin/bash","-c","-param:instId=1020 01",cmd};
            String[] aa = new String[]{"/bin/sh",cmd3};

            Process process = Runtime.getRuntime().exec(cmd3,null,null);
            //Process process = Runtime.getRuntime().exec(cmd2,null,null);

            process.waitFor();
            OutputStream outputStream = process.getOutputStream();
            InputStream inputStream = process.getErrorStream();
            System.out.println(">.");
        }catch (Exception e){
            e.printStackTrace();
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
