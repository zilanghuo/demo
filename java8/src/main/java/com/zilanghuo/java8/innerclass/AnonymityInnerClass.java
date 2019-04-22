package com.zilanghuo.java8.innerclass;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 下午 4:37
 * 匿名内部类
 */
public class AnonymityInnerClass {
    public void test(Bird bird) {
        System.out.println(bird.getName() + "飞了" + bird.fly() + "米");
    }
    public static void main(String[] args) {
        AnonymityInnerClass innerClass = new AnonymityInnerClass();
        innerClass.test(new Bird() {
            @Override
            public int fly() {
                return 100;
            }
            @Override
            public String getName() {
                return "大雁";
            }
        });
    }
}
