package com.zilanghuo.lcbtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author laiwufa
 * @date 2019/4/9 0009 上午 10:03
 */
public class HealthTest extends LcbTestApplicationTests{

    @Autowired
    protected WebApplicationContext wac;

    @Test
    public void test() throws Exception {
        RequestBuilder request = null;
        request = post("/test");
        mvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("ok"));
        Thread.sleep(3 * 1000);
    }
}