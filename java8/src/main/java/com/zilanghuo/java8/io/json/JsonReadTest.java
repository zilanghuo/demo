package com.zilanghuo.java8.io.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.*;
import java.util.List;

/**
 * @author laiwufa
 * @date 2019/3/4 0004 上午 9:22
 */
public class JsonReadTest {


    public static void main(String[] args) {
        String s = readFileByChars("E:\\json.txt");
        System.out.println(s);
        convertJson2Bean(s);
    }

    public static Result convertJson2Bean(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        Result result = jsonObject.toJavaObject(Result.class);
        return result;
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static String readFileByChars(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    stringBuffer.append(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            stringBuffer.append(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return stringBuffer.toString();
    }


}

@Data
class Result implements Serializable {

    private static final long serialVersionUID = -3273974193806726050L;

    public TotalResult data;

}

@Data
class TotalResult implements Serializable {
    private static final long serialVersionUID = 345689637100345171L;

    public List<ServiceResult> getTopNSlowService;

}

@Data
class ServiceResult implements Serializable {
    private static final long serialVersionUID = -5000911391300900536L;

    public ServiceBean service;

    public Long value;

}

@Data
class ServiceBean implements Serializable {

    private static final long serialVersionUID = -4355458906767416434L;

    public String key;

    public String label;

    public String applicationId;

    public String applicationName;
}

