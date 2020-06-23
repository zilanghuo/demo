package com.zilanghuo.test.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

/**
 * Created by laiwufa on 2020-06-23
 */
public class KafkaProducerKerberos {

    public static void main(String[] args) {
        //在本地中设置JAAS，也可以通过-D方式传入，conf文件里面需要配置keytab文件的位置
        System.setProperty("java.security.auth.login.config", "/Users/admin/Documents/officeFile/kafka/kafka_client_jaas.conf");
        System.setProperty("java.security.krb5.conf", "/Users/admin/Documents/officeFile/kafka/krb5.conf");
        //在Linux中设置JAAS，也可以通过-D方式传入

        Properties props = new Properties();
        props.put("bootstrap.servers", "nn205.uat.yonghui.cn:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.kerberos.service.name", "kafka");
        props.put("sasl.mechanism", "GSSAPI");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        String topic = "operationLog";

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print(">>");
            String message = scan.nextLine();
            producer.send(new ProducerRecord<String, String>(topic, message));
            System.out.println(message);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
