package com.zilanghuo.java8.io.output;

import java.io.*;

/**
 * @author laiwufa
 * @date 2019/3/20 0020 上午 9:46
 */
public class Test {

    public static void main(String[] args) throws Exception {
        // inputStr("nidaf你好","/Users/admin/Desktop/logs/b.sql");
        System.out.println(getContent("/Users/admin/Desktop/logs/b.sql"));
    }

    private static String getContent(String filePath) throws Exception{
        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader reader  = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        while (( line = reader.readLine()) != null){
            builder.append("\n").append(line);
        }
        reader.close();
        inputStream.close();
        return builder.toString().substring(1);
    }

    private static void inputStr(String str,String filePath) throws Exception{
        FileOutputStream  out = new FileOutputStream(filePath,false);
        out.write(str.getBytes());
        out.flush();
        out.close();
    }

    private static void inputTest() throws IOException {
        File file = new File("E:/json.txt");
        DataOutputStream  out = new DataOutputStream(new FileOutputStream("E:/json2.txt"));
        FileInputStream inputStream = new FileInputStream(file);
/*        byte[] readbyte = new byte[1024];
        int offset = inputStream.read(readbyte);
        while (offset != -1) {
           // System.out.print(new String(readbyte));
            for (int i = 0; i < offset; i++) {
                System.out.print((char) readbyte[i]);
            }
            offset = inputStream.read(readbyte);
            System.out.println("----------offset:" + offset);
        }*/
        BufferedReader d  = new BufferedReader(new InputStreamReader(inputStream));
        String count;
        while((count = d.readLine()) != null){
            String u = count.toUpperCase();
            System.out.println(u);
            out.writeBytes(u + "  ,");
        }
        d.close();



    }

}
