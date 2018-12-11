package com.zilanghuo.queue.code.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author laiwufa
 * @date 2018/12/11
 * use:
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("103.45.109.87");
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setVirtualHost("/");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (int i = 0; i < 20; i++) {
                String message = "Hello World!" + i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }

}
