package com.zilanghuo.queue.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author laiwufa
 * @date 2018/12/3
 * use:
 */
@Component
public class RabbitProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        System.out.println("send message:" + message);
        rabbitTemplate.convertAndSend("queue_one", message);
    }
}
