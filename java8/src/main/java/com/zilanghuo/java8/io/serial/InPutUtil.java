package com.zilanghuo.java8.io.serial;

import java.io.*;

/**
 * @author laiwufa
 * @date 2018/11/2
 * use:
 */
public class InPutUtil {


    /**
     * 字节流读取
     *
     * @param file
     */
    public static void fileInputRead(File file) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件-ObjectInputStream
     *
     * @param file
     */
    public static Object read(File file) {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
            return is.readObject(); // 从流中读取User的数据
        } catch (Exception e) {
        }
        return null;
    }
}
