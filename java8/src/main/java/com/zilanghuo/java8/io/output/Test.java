package com.zilanghuo.java8.io.output;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @author laiwufa
 * @date 2019/3/20 0020 上午 9:46
 */
public class Test {

    public static void main(String[] args) throws Exception {
        test5();
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
    public static void test5() throws IOException {
        // TODO Auto-generated method stub
        String inputFile = "/Users/admin/Documents/aa.log";
        String outputFile = "/Users/admin/Documents/file16.log";


        long start = System.currentTimeMillis();//获取当前时间
        int BUFFER_SIZE = 1024;
        byte[] buffer = new byte[BUFFER_SIZE];

        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
        int count,i;
        while((count=inputStream.read(buffer))!=-1){
            // for循环保证只写入count个byte, 否则会写入1024个byte
            /*for(i=0; i<count; i++){
                outputStream.write(buffer[i]);
            }*/
            outputStream.write(buffer,0,count);
        }
        outputStream.flush();



/*


        int BUFFER_SIZE = 100;
        byte[] buffer = new byte[BUFFER_SIZE];
        int i=1;
        int size=0;
        while ((size = inputStream.read(buffer)) != -1) {
            System.out.println("size:"+size);
            System.out.println(i);
            i++;
            outputStream.write(buffer);
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        inputStream.close();
        outputStream.close();
*/

        return;

    }


}
