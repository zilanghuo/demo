package com.zilanghuo.queue.code.send.queue;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.UUID;

/**
 * @author laiwufa
 * @date 2018/12/11
 * use:
 */
public class SendQueue {

    private final static String QUEUE_NAME = "send";

    private final static String REPLY_NAME = "reply";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("103.45.109.87");
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setVirtualHost("/");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // 监听回调队列
            channel.basicConsume(REPLY_NAME, true, new ReplyConsumer(channel));

            // 发送模块
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (int i = 0; i < 10; i++) {
                String message = "Hello World!" ;
                // 定义回调队列
                AMQP.BasicProperties props = new AMQP.BasicProperties
                        .Builder()
                        .replyTo(REPLY_NAME)
                        .correlationId(UUID.randomUUID().toString())
                        .build();
             //   channel.basicPublish("", QUEUE_NAME, props, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
        Thread.sleep(100 * 1000);
    }
}
