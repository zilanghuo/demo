package com.zilanghuo.java8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by laiwufa on 2020-08-18
 */
public class TestOne {

    public static void main(String[] args) throws Exception{

            String ss = "version-20200902143247-test02-1599028367914-21fe996";
        System.out.println(ss.substring(8,22));


    }

    public static void testaa() throws Exception{

            /*String cmdString = "python3.8 /Users/admin/Desktop/python_task_5min.py " +
                    "> /Users/admin/Desktop/Input.txt " +
                    "2>/Users/admin/Desktop/error.txt; " +
                    " echo $? > /Users/admin/Desktop/test2.txt";*/

        String cmdString = "java -jar /Users/admin/Desktop/silkwormlogtohdfs-1.0-SNAPSHOT.jar " +
                "> /Users/admin/Desktop/Input.txt " +
                "2>/Users/admin/Desktop/error.txt;" +
                "echo $? > /Users/admin/Desktop/test2.txt";

        String[] cmd1 = {"/bin/sh", "-c", cmdString};

        Process process = Runtime.getRuntime().exec(cmd1);
        process.waitFor();
            /*BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorInput = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            String error = null;
            while ((line = input.readLine()) != null) {


                System.out.println(line + ">>>>input");
            }
            while ((error = errorInput.readLine()) != null) {
                System.out.println(error+">>>>>error");
            }

            Thread.sleep(600);*/

        // Thread.sleep(600);
    }
}
