package com.zilanghuo.queue.rabbit;

import com.zilanghuo.queue.config.ParameterConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
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
    private ParameterConfig parameterConfig;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        System.out.println("send message:" + message);
        rabbitTemplate.convertAndSend(parameterConfig.getQueueName(), message);
    }

}
