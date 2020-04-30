package com.zilanghuo.java8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by laiwufa on 2020-02-12
 */
public class MatchDemo {

    public static void main(String[] args) {
        MatchDemo matchDemo = new MatchDemo();
        System.out.println(matchDemo.isDigit("00233"));
        System.out.println(Long.parseLong("00323"));

    }

    // 判断一个字符串是否都为数字
    public boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }
    //截取数字
    public String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 截取非数字
    public String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 判断一个字符串是否含有数字
    public boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }
}
