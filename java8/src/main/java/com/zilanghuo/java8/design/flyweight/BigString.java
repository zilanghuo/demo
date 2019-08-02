package com.zilanghuo.java8.design.flyweight;

/**
 * @author laiwufa
 * @date 2019/8/2 9:15
 */
public class BigString {

    private BigChar[] bigChars;

    public BigString(String string) {
        bigChars = new BigChar[string.length()];
        BigCharFactory bigCharFactory = BigCharFactory.getInstance();
        for (int i = 0; i < string.length(); i++) {
            bigChars[i] = bigCharFactory.getBigChar(Integer.valueOf(string.charAt(i)));
        }
    }

    public void print() {
        for (int i = 0; i < bigChars.length; i++) {
            bigChars[i].print();
        }

    }
}
