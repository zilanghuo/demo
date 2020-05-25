package com.zilanghuo.java8.velovcity;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 * Created by laiwufa on 2020-04-30
 */
public class VelocityHelloWorld {

    public static void main(String args[]) {
        /* 1.初始化 Velocity */
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(VelocityEngine.RESOURCE_LOADER, "file");
        velocityEngine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "/Users/admin/Desktop/avatar");
        velocityEngine.init();

        /* 2.创建一个上下文对象 */
        VelocityContext context = new VelocityContext();

        /* 3.添加你的数据对象到上下文 */
        context.put("name", "Victor Zhang");
        context.put("project", "Velocity");

        /* 4.选择一个模板 */
        Template template = velocityEngine.getTemplate("hello.vm");

        /* 5.将你的数据与模板合并，产生输出内容 */
        StringWriter sw = new StringWriter();
        template.merge(context, sw);
        System.out.println("final output:\n" + sw);
    }
}
