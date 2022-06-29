package com.zilanghuo.canal.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by laiwufa on 2022-04-23
 */
public class KafkaMain {

    public static void main(String[] args) {


        Properties props = new Properties();
        props.put("bootstrap.servers", "");
        props.put("group.id", "test2121");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        String topic = "";

        // 获取topic的partition信息
        List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
        List<TopicPartition> topicPartitions = new ArrayList<>();
        Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();
        long fetchDataTime = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

        for (PartitionInfo partitionInfo : partitionInfos) {
            topicPartitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
            timestampsToSearch.put(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()), fetchDataTime);
        }
        consumer.assign(topicPartitions);
        // 获取每个partition偏移量
        Map<TopicPartition, OffsetAndTimestamp> map = consumer.offsetsForTimes(timestampsToSearch);
        OffsetAndTimestamp offsetTimestamp = null;
        System.out.println("开始设置各分区初始偏移量...");
        for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : map.entrySet()) {
            // 如果设置的查询偏移量的时间点大于最大的索引记录时间，那么value就为空
            offsetTimestamp = entry.getValue();
            if (offsetTimestamp != null) {
                int partition = entry.getKey().partition();
                long timestamp = offsetTimestamp.timestamp();
                long offset = offsetTimestamp.offset();
                // 设置读取消息的偏移量
                consumer.seek(entry.getKey(), offset);
            }
        }
        System.out.println("设置各分区初始偏移量结束...");
        int count = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();

            }
        }

    }
}
