package com.zilanghuo.log;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Created by laiwufa on 2021-01-07
 */
public class MqClient {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("test");
        producer.setNamesrvAddr("10.210.14.15:9876;10.210.14.42:9876;10.210.14.49:9876");
        //producer.setNamesrvAddr("10.251.129.24:9876");
        try {
            producer.start();
            Message msg = new Message();
            msg.setTopic("sjzt-test");
            msg.setTags("1");
            ReceiveUserDTO userDTO = new ReceiveUserDTO();
            userDTO.setUserCode("00999999");
            userDTO.setType(1);
            userDTO.setPassword("1234qwer");
            userDTO.setUserName("测试");
            String str = JSON.toJSONString(userDTO);

            msg.setBody(str.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult send = producer.send(msg);
            System.out.println(JSON.toJSONString(send));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        producer.shutdown();
    }

}
