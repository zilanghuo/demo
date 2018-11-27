package com.zilanghuo.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author laiwufa
 * @date 2018/11/26  22:42
 */
public class App {

    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("=========premain start========");
        // 注册ClassFileTransformer
        inst.addTransformer(new FirstAgent());
    }

}
