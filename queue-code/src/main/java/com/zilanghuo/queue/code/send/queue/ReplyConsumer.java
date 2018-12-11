package com.zilanghuo.queue.code.send.queue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author laiwufa
 * @date 2018/12/11
 * use:
 */
public class ReplyConsumer extends DefaultConsumer {


    public ReplyConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("收到回调信息：" + body.toString() + ",id为：" + properties.getCorrelationId());
    }

}
