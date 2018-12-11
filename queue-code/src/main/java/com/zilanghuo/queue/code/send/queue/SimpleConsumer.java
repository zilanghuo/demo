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
public class SimpleConsumer extends DefaultConsumer {


    public SimpleConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("进入RPC方法调用,参数：" + body.toString());
        //调用服务
        String reply = properties.getReplyTo();
        String id = properties.getCorrelationId();

        System.out.println("消息处理成功-回调队列：" + reply);
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().replyTo(reply).correlationId(id).build();
        //放入回调的队列
        this.getChannel().basicPublish("", reply, props, ("成功").getBytes());
        System.out.println("消息回复成功");
    }

}
