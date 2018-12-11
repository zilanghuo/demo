package com.zilanghuo.queue.code.send.queue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author laiwufa
 * @date 2018/12/11
 * use:
 */
public class RecvQueue {

    private final static String QUEUE_NAME = "send";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("103.45.109.87");
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 消费队列
        channel.basicConsume("",true,new SimpleConsumer(channel));
    }
}
