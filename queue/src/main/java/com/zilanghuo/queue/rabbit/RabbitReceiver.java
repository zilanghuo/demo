package com.zilanghuo.queue.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author laiwufa
 * @date 2018/12/3
 * use:
 */
@Component
@RabbitListener(queues = "queue_one")
public class RabbitReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("Receiver : " + msg);
    }
}
