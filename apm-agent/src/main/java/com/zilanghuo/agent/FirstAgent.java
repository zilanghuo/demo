package com.zilanghuo.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author laiwufa
 * @date 2018/11/26  22:33
 */
public class FirstAgent implements ClassFileTransformer {

    public final String injectedClassName = "com.xueyou.agentdemo.App";

    public final String methodName = "hello";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("className:" + className);
        return null;
    }
}
