package com.zilanghuo.java8.design.adapter;

/**
 * @author laiwufa
 * @date 2019/4/8 0008 下午 4:18
 */
public class TestMain {

    public static void main(String[] args) {
        TwoPlug twoPlug = new TrianglePlug();
        ThreePlug threePlug = new RoundThreePlug();

        TwoPlug2ThreeAdapter adapter = new TwoPlug2ThreeAdapter(twoPlug);
        System.out.println("the twoPlug function...");
        twoPlug.twoJack();

        System.out.println("the threePlug function....");
        testThreePlug(threePlug);

        System.out.println("the adapter says.....");
        testThreePlug(adapter);
    }

    static void testThreePlug(ThreePlug threePlug){
        threePlug.threePlugIn();
        threePlug.out();
    }

}
