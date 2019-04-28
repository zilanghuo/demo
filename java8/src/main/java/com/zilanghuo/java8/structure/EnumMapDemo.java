package com.zilanghuo.java8.structure;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author laiwufa
 * @date 2019/4/28 0028 上午 11:51
 * 线程不安全：EnumMap
 */
public class EnumMapDemo {

    private EnumMap<Color, String> enumMap = new EnumMap<Color, String>(Color.class);

    {
        enumMap.put(Color.RED, "red");
        enumMap.put(Color.YELLOW, "yellow");
        enumMap.put(Color.BLANK, "blank");
    }

    public static void main(String[] args) {
        EnumMapDemo demo = new EnumMapDemo();
        if (demo.enumMap != null && !demo.enumMap.isEmpty()) {
            Iterator<Map.Entry<Color, String>> iterator = demo.enumMap.entrySet().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next().getValue());
            }
        }
    }
}


enum Color {
    RED(), YELLOW(), BLANK();
}
