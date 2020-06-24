package com.zilanghuo.test.kafka;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by laiwufa on 2020-06-23
 */
public class KafkaProducerNoKerberos implements Runnable{

    private final KafkaProducer<String, String> producer;

    private final String topic;


    public KafkaProducerNoKerberos(String topicName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        //acks=0：如果设置为0，生产者不会等待kafka的响应。
        //acks=1：这个配置意味着kafka会把这条消息写到本地日志文件中，但是不会等待集群中其他机器的成功响应。
        //acks=all：这个配置意味着leader会等待所有的follower同步完成。这个确保消息不会丢失，除非kafka集群中所有机器挂掉。这是最强的可用性保证。
        props.put("acks", "all");
        //配置为大于0的值的话，客户端会在消息发送失败时重新发送。
        props.put("retries", 0);
        //当多条消息需要发送到同一个分区时，生产者会尝试合并网络请求。这会提高client和生产者的效率
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<String, String>(props);
        this.topic = topicName;
    }

    @Override
    public void run() {
        int messageNo = 1;
        try {
            for(;;) {

                String json ="{\"data\":[{\"id\":\"3275454\",\"order_no\":\"926585288000641\",\"product_code\":\"837440\",\"out_product_code\":\"2024953427\",\"product_name\":\"立白精致衣物护理洗衣液\",\"product_url\":\"http://hotfile.yonghui.cn/files/|cephdata|filecache|YHYS|YHYS|2019-08-25|1726037918423715840\",\"category_code\":\"103001\",\"category_name\":\"衣物洗涤\",\"has_group_item\":\"0\",\"unit\":\"组\",\"spec\":\"2kg*2\",\"quantity\":\"1.0\",\"sell_price\":\"7990\",\"amount\":\"7990\",\"share_unit_price\":\"2212\",\"share_price\":\"2212\",\"promotional_offers\":\"5778\",\"promotion_code\":\"127019488\",\"promotion_type\":\"4\",\"promotion_name\":\"\",\"promotion_fee\":\"0\",\"created_by\":\"固建春\",\"created_time\":\"2019-11-04 16:49:20\",\"updated_by\":\"固建春\",\"update_time\":\"2019-11-04 16:49:20\"}],\"database\":\"yh_sod_shop_sale_order\",\"es\":1572857360000,\"id\":1905547,\"isDdl\":false,\"mysqlType\":{\"id\":\"bigint(20)\",\"order_no\":\"varchar(32)\",\"product_code\":\"varchar(32)\",\"out_product_code\":\"varchar(32)\",\"product_name\":\"varchar(64)\",\"product_url\":\"varchar(1024)\",\"category_code\":\"varchar(32)\",\"category_name\":\"varchar(64)\",\"has_group_item\":\"int(2)\",\"unit\":\"varchar(5)\",\"spec\":\"varchar(30)\",\"quantity\":\"decimal(13,3)\",\"sell_price\":\"int(11)\",\"amount\":\"int(11)\",\"share_unit_price\":\"int(11)\",\"share_price\":\"int(11)\",\"promotional_offers\":\"int(11)\",\"promotion_code\":\"varchar(32)\",\"promotion_type\":\"int(2)\",\"promotion_name\":\"varchar(64)\",\"promotion_fee\":\"int(11)\",\"created_by\":\"varchar(255)\",\"created_time\":\"datetime\",\"updated_by\":\"varchar(255)\",\"update_time\":\"datetime\"},\"old\":null,\"pkNames\":[\"id\"],\"sql\":\"\",\"sqlType\":{\"id\":-5,\"order_no\":12,\"product_code\":12,\"out_product_code\":12,\"product_name\":12,\"product_url\":12,\"category_code\":12,\"category_name\":12,\"has_group_item\":4,\"unit\":12,\"spec\":12,\"quantity\":3,\"sell_price\":4,\"amount\":4,\"share_unit_price\":4,\"share_price\":4,\"promotional_offers\":4,\"promotion_code\":12,\"promotion_type\":4,\"promotion_name\":12,\"promotion_fee\":4,\"created_by\":12,\"created_time\":93,\"updated_by\":12,\"update_time\":93},\"table\":\"yh_platform_order_item\",\"ts\":1572857360500,\"type\":\"INSERT\"}";
                Future<RecordMetadata> message = producer.send(new ProducerRecord<String, String>(topic, "Message", json));
                System.out.println(message.isDone());
                //生产了10条就打印
                if(messageNo%10==0){
                    System.out.println("发送的信息:" + json);
                }
                //生产100条就退出
                if(messageNo%100 ==0){
                    System.out.println("成功发送了"+messageNo+"条");
                    break;
                }
                messageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    public static void main(String args[]) {
        KafkaProducerNoKerberos test = new KafkaProducerNoKerberos("operationLog");//log_test
        Thread thread = new Thread(test);
        thread.start();
    }

}
