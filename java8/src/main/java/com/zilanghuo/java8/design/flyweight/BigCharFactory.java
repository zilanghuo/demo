package com.zilanghuo.java8.design.flyweight;

import java.util.HashMap;

/** bigChar 工厂，单例对象
 * @author laiwufa
 * @date 2019/8/2 9:09
 */
public class BigCharFactory {

    private final static BigCharFactory factory = new BigCharFactory();
    private BigCharFactory(){}

    private HashMap<Integer,BigChar> pool = new HashMap<>(18);

    public BigChar getBigChar(Integer charName){
        if (null == pool.get(charName)){
            BigChar bigChar = new BigChar();
            bigChar.setCharName(charName);
            bigChar.setFontName(charName+"----");
            pool.put(charName,bigChar);
            return bigChar;
        }
        return pool.get(charName);
    }

    public static BigCharFactory getInstance(){
        return factory;
    }
}
