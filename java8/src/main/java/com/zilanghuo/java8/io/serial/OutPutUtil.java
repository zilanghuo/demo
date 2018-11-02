package com.zilanghuo.java8.io.serial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

}
