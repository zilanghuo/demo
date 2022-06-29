package com.zilanghuo.canal.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

/**
 * Created by laiwufa on 2022-04-23
 */
public class CustomConsumer {

    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config","D:\\kafka\\jaas.conf");
        System.setProperty("java.security.krb5.conf","D:\\kafka\\krb5.conf");

        System.setProperty("javax.security.auth.useSubjectCredsOnly", "true");

        // 0 配置
        Properties properties = new Properties();
        // 连接到服务器
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "yhshkfkslave1.yonghui.cn:9092,yhshkfkslave2.yonghui.cn:9092,yhshkfkslave3.yonghui.cn:9092");

        // 反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 添加groupid,必须
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "laiwufa-test");

        // 1 创建一个消费者对象
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);

        // 2 订阅主题
        ArrayList<String> topics = new ArrayList<String>();
        topics.add("Mytopic");
        kafkaConsumer.subscribe(topics); // 注册要消费的主题（可以消费多个主题）

        // 指定位置开始消费
        Set<TopicPartition> assignment = new HashSet<>();
        while (assignment.size() == 0) {
            kafkaConsumer.poll(10);
            // 获取消费者分区分配信息（有了分区分配信息才能开始消费）
            assignment = kafkaConsumer.assignment();
        }

        // 把时间转换为对应的offset
        HashMap<TopicPartition, Long> partitionLongHashMap = new HashMap<>();
        // 封装对应的集合
        for (TopicPartition topicPartition : assignment) {
            partitionLongHashMap.put(topicPartition, System.currentTimeMillis() - 1 * 24 * 3600 * 1000); // 一天前的时间System.currentTimeMillis() - 1*24*3600*100
        }

        Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = kafkaConsumer.offsetsForTimes(partitionLongHashMap);


        // 遍历所有分区，并指定 offset 从前一天开始消费
        for (TopicPartition topicPartition : assignment) {
            OffsetAndTimestamp offsetAndTimestamp = topicPartitionOffsetAndTimestampMap.get(topicPartition);
            kafkaConsumer.seek(topicPartition, offsetAndTimestamp.offset());
        }

        // 3 消费数据
        // 一直获取消费数据
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(10);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }
        }
    }
}
