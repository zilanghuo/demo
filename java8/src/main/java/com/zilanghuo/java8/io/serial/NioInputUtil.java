package com.zilanghuo.java8.io.serial;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laiwufa
 * @date 2018/11/2
 * use:
 */
public class NioInputUtil {

    /**
     * nio-fileInputStream的实现
     *
     * @param file
     */
    public static List<String> randomAccessInput(File file) {
        List<String> list = new ArrayList();

        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(file, "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                StringBuffer sb = new StringBuffer();
                while (buf.hasRemaining()) {
                    char a = (char) buf.get();
                    System.out.print(a);
                    sb.append(a);
                }
                list.add(sb.toString());
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        List<String> strings = readLine(new File("H:\\1.txt"));
       for (int i = 0; i < strings.size(); i++) {
            String fileName = strings.get(i);
           String totalFileName = "H:\\" + fileName + ".xlsx";
           System.out.println(totalFileName);
            File in = new File("H:\\710242903.xlsx");
            File out = new File(totalFileName);
            copyFileUsingFileStreams(in, out);
        }

    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }

    public static List<String> readLine(File file) throws Exception{
        List<String> list = new ArrayList();
        FileInputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            System.out.println(str);
            list.add(str);
        }
        inputStream.close();
        bufferedReader.close();
        return list;
    }

}
