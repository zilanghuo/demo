package com.zilanghuo.java8.io.serial;

import java.io.*;

/**
 * @author laiwufa
 * @date 2018/11/2
 * use:
 */
public class OutPutUtil {

    /**
     * 将对象输出到文件
     * @param file
     * @param user
     */
    public static void write(File file,Object user){
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream(file));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void write(File file, InputStream inputStream) throws Exception{
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(inputStream.read());
        outputStream.flush();
        outputStream.flush();

    }

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/admin/Desktop/avatar/test/a.txt");
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        //write(new File("/Users/admin/Desktop/avatar/test/b.txt"),in);
        NioInputUtil.copyFileUsingFileStreams(file,new File("/Users/admin/Desktop/avatar/test/b.txt"));
    }

}
