package com.zilanghuo.test.silkworm;

import java.io.*;

/**
 * Created by laiwufa on 2020-07-01
 */
public class ShellWorker {

    public static void main(String[] args) {

        try {
            String stdout = "/Users/admin/Desktop/office/test/stdout.log";
            String error = "/Users/admin/Desktop/office/test/error.log";
            String quit = "/Users/admin/Desktop/office/test/quit.log";
            String filePath = "/Users/admin/Desktop/office/test/task_16s.py";

            //  String cmd = "python /Users/admin/Desktop/office/test/task_16s.py >/Users/admin/Desktop/office/test/stdout.log 2>/Users/admin/Desktop/office/test/error.log";
            //  String cmdSh = "/bin/bash /Users/admin/Desktop/office/test/python.sh >/Users/admin/Desktop/office/test/stdout.log 2>/Users/admin/Desktop/office/test/error.log";
            String[] cmdShArrPython = {"/bin/bash", "-c", "/usr/bin/python /Users/admin/Desktop/office/test/task_16s.py >/Users/admin/Desktop/office/test/stdout1.log 2>/Users/admin/Desktop/office/test/error1.log\n" +
                    " echo $? > /Users/admin/Desktop/office/test/stdout1.exit"};

            Process process = Runtime.getRuntime().exec(cmdShArrPython);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorInput = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            String errorLog = null;

            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

            while ((errorLog = errorInput.readLine()) != null) {
                System.out.println(errorLog);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @org.junit.Test
    public void readFile() throws Exception {
        // 获取退出码文件
        String logFile = "/Users/admin/Desktop/office/test/stdout1.log";
        String quitCode = "";
        String quitLogFile = logFile.substring(0, logFile.length() - 4) + ".exit";
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(quitLogFile))));
            quitCode = input.readLine();
        } catch (Exception e) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        System.out.println("---"+quitCode);
    }
}
