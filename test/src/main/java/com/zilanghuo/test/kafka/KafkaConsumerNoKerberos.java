package com.zilanghuo.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by laiwufa on 2020-06-23
 */
public class KafkaConsumerNoKerberos {

    public static void main(String[] args) {
        //在本地中设置JAAS

        Properties props = new Properties();
        // 定义kakfa 服务的地址，不需要将所有broker指定上
        props.put("bootstrap.servers", "127.0.0.1:9092");
        // 制定consumer group
         props.put("group.id", "hhtest");
        // 是否自动确认offset
        props.put("enable.auto.commit", "true");
        props.put("auto.offset.reset", "earliest");
        // 自动确认offset的时间间隔
        props.put("auto.commit.interval.ms", "1000");
        // key的序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value的序列化类
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 定义consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // 消费者订阅的topic, 可同时订阅多个
        consumer.subscribe(Collections.singletonList("operationLog"));

        while (true) {
            System.out.println("--");
            // 读取数据，读取超时时间为100ms
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition = %s, offset = %d, key = %s, value = %s%n",record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }
}
