package com.zilanghuo.queue;

import com.zilanghuo.queue.rabbit.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiwufa
 * @date 2018/12/3
 * use:
 */
@RestController
public class SampleController {

    @Autowired
    private RabbitProducer rabbitProducer;

    @RequestMapping("/")
    String home() {
        rabbitProducer.send("hello world!");
        return "Hello World!";
    }
}
