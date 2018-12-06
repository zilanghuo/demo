package com.zilanghuo.queue.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author laiwufa
 * @date 2018/12/3
 * use:
 */
@Component
@RabbitListener(queues = "${queueName}")
public class RabbitReceiver {

    @RabbitHandler
    public void process(@Payload String body, @Headers Map<String, Object> headers) {
        System.out.println("Receiver : " + body);
        System.out.println("headers : " + headers);
    }
}
