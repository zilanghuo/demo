package com.zilanghuo.canal.ssh;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by laiwufa on 2022-07-25
 */
public class PropertiesUtil {

    public Properties load(String propertiesString) {
        Properties properties = new Properties();
        try {
            properties.load(new ByteArrayInputStream(propertiesString.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) {
        String str = "#################################################\n" +
                "######### \t\tcommon argument\t\t#############\n" +
                "#################################################\n" +
                "# tcp bind ip\n" +
                "canal.ip =\n" +
                "# register ip to zookeeper\n" +
                "#canal.register.ip =\n" +
                "canal.port = 11111\n" +
                "canal.metrics.pull.port = 20067\n" +
                "# canal instance user/passwd\n" +
                "# canal.user = canal\n" +
                "# canal.passwd = E3619321C1A937C46A0D8BD1DAC39F93B27D4458\n" +
                "\n" +
                "# canal admin config\n" +
                "#canal.admin.manager = 127.0.0.1:8089\n" +
                "#canal.admin.port = 11110\n" +
                "#canal.admin.user = admin\n" +
                "#canal.admin.passwd = 4ACFE3202A5FF5CF467898FC58AAB1D615029441\n" +
                "\n" +
                "canal.zkServers = shd219.yonghui.cn:2181,shd212.yonghui.cn:2181,shd215.yonghui.cn:2181\n" +
                "# flush data to zk\n" +
                "canal.zookeeper.flush.period = 1000\n" +
                "canal.withoutNetty = false\n" +
                "# tcp, kafka, RocketMQ\n" +
                "canal.serverMode = kafka\n" +
                "canal.mq.properties.zkStr = shd219.yonghui.cn:2181,shd212.yonghui.cn:2181,shd215.yonghui.cn:2181\n" +
                "# flush meta cursor/parse position to file\n" +
                "canal.file.data.dir = ${canal.conf.dir}\n" +
                "canal.file.flush.period = 1000\n" +
                "## memory store RingBuffer size, should be Math.pow(2,n)\n" +
                "canal.instance.memory.buffer.size = 16384\n" +
                "## memory store RingBuffer used memory unit size , default 1kb\n" +
                "canal.instance.memory.buffer.memunit = 1024 \n" +
                "## meory store gets mode used MEMSIZE or ITEMSIZE\n" +
                "canal.instance.memory.batch.mode = MEMSIZE\n" +
                "canal.instance.memory.rawEntry = true\n" +
                "\n" +
                "## detecing config\n" +
                "canal.instance.detecting.enable = true\n" +
                "canal.instance.detecting.sql = insert into retl.xdual values(1,now()) on duplicate key update x=now()\n" +
                "#canal.instance.detecting.sql = select 1\n" +
                "canal.instance.detecting.interval.time = 3\n" +
                "canal.instance.detecting.retry.threshold = 3\n" +
                "canal.instance.detecting.heartbeatHaEnable = false\n" +
                "\n" +
                "# support maximum transaction size, more than the size of the transaction will be cut into multiple transactions delivery\n" +
                "canal.instance.transaction.size =  1024\n" +
                "# mysql fallback connected to new master should fallback times\n" +
                "canal.instance.fallbackIntervalInSeconds = 60\n" +
                "\n" +
                "# network config\n" +
                "canal.instance.network.receiveBufferSize = 16384\n" +
                "canal.instance.network.sendBufferSize = 16384\n" +
                "canal.instance.network.soTimeout = 30\n" +
                "\n" +
                "# binlog filter config\n" +
                "canal.instance.filter.druid.ddl = true\n" +
                "canal.instance.filter.query.dcl = false\n" +
                "canal.instance.filter.query.dml = false\n" +
                "canal.instance.filter.query.ddl = false\n" +
                "canal.instance.filter.table.error = false\n" +
                "canal.instance.filter.rows = false\n" +
                "canal.instance.filter.transaction.entry = false\n" +
                "\n" +
                "# binlog format/image check\n" +
                "canal.instance.binlog.format = ROW\n" +
                "canal.instance.binlog.image = FULL,MINIMAL,NOBLOB\n" +
                "\n" +
                "# binlog ddl isolation\n" +
                "canal.instance.get.ddl.isolation = false\n" +
                "\n" +
                "# parallel parser config\n" +
                "canal.instance.parser.parallel = true\n" +
                "## concurrent thread number, default 60% available processors, suggest not to exceed Runtime.getRuntime().availableProcessors()\n" +
                "#canal.instance.parser.parallelThreadSize = 16\n" +
                "## disruptor ringbuffer size, must be power of 2\n" +
                "canal.instance.parser.parallelBufferSize = 256\n" +
                "\n" +
                "# table meta tsdb info\n" +
                "canal.instance.tsdb.enable = true\n" +
                "canal.instance.tsdb.dir = ${canal.file.data.dir:../conf}/${canal.instance.destination:}\n" +
                "canal.instance.tsdb.url = jdbc:h2:${canal.instance.tsdb.dir}/h2;CACHE_SIZE=1000;MODE=MYSQL;\n" +
                "canal.instance.tsdb.dbUsername = canal\n" +
                "canal.instance.tsdb.dbPassword = canal\n" +
                "# dump snapshot interval, default 24 hour\n" +
                "canal.instance.tsdb.snapshot.interval = 24\n" +
                "# purge snapshot expire , default 360 hour(15 days)\n" +
                "canal.instance.tsdb.snapshot.expire = 360\n" +
                "\n" +
                "# aliyun ak/sk , support rds/mq\n" +
                "canal.aliyun.accessKey =\n" +
                "canal.aliyun.secretKey =\n" +
                "\n" +
                "#################################################\n" +
                "######### \t\tdestinations\t\t#############\n" +
                "#################################################\n" +
                "canal.destinations = example\n" +
                "# conf root dir\n" +
                "canal.conf.dir = ../conf\n" +
                "# auto scan instance dir add/remove and start/stop instance\n" +
                "canal.auto.scan = true\n" +
                "canal.auto.scan.interval = 5\n" +
                "\n" +
                "canal.instance.tsdb.spring.xml = classpath:spring/tsdb/h2-tsdb.xml\n" +
                "#canal.instance.tsdb.spring.xml = classpath:spring/tsdb/mysql-tsdb.xml\n" +
                "\n" +
                "canal.instance.global.mode = spring\n" +
                "canal.instance.global.lazy = false\n" +
                "canal.instance.global.manager.address = ${canal.admin.manager}\n" +
                "#canal.instance.global.spring.xml = classpath:spring/memory-instance.xml\n" +
                "#canal.instance.global.spring.xml = classpath:spring/file-instance.xml\n" +
                "canal.instance.global.spring.xml = classpath:spring/default-instance.xml\n" +
                "\n" +
                "##################################################\n" +
                "######### \t\t     MQ \t\t     #############\n" +
                "##################################################\n" +
                "canal.mq.servers = yhshkfkslave1.yonghui.cn:9092,yhshkfkslave2.yonghui.cn:9092,yhshkfkslave3.yonghui.cn:9092\n" +
                "canal.mq.retries = 0\n" +
                "canal.mq.batchSize = 16384\n" +
                "canal.mq.maxRequestSize = 1048576\n" +
                "canal.mq.lingerMs = 100\n" +
                "canal.mq.bufferMemory = 33554432\n" +
                "canal.mq.canalBatchSize = 50\n" +
                "canal.mq.canalGetTimeout = 100\n" +
                "canal.mq.flatMessage = true\n" +
                "canal.mq.compressionType = none\n" +
                "canal.mq.acks = all\n" +
                "#canal.mq.properties. =\n" +
                "canal.mq.producerGroup = test\n" +
                "# Set this value to \"cloud\", if you want open message trace feature in aliyun.\n" +
                "canal.mq.accessChannel = local\n" +
                "# aliyun mq namespace\n" +
                "#canal.mq.namespace =\n" +
                "\n" +
                "##################################################\n" +
                "#########     Kafka Kerberos Info    #############\n" +
                "##################################################\n" +
                "#canal.mq.kafka.kerberos.enable = false\n" +
                "#canal.mq.kafka.kerberos.krb5FilePath = \"../conf/kerberos/krb5.conf\"\n" +
                "#canal.mq.kafka.kerberos.jaasFilePath = \"../conf/kerberos/jaas.conf\"\n" +
                "\n" +
                "#�Զ���\n" +
                "canal.mq.kafka.ddlTopic= cdcx_ddl_topic\n" +
                "canal.mq.kafka.allowEventTimestamp= false\n" +
                "canal.mq.kafka.kerberos.enable = true\n" +
                "canal.mq.kafka.kerberos.krb5FilePath = /data/cdcx/cdcx_manager/etc/kerberos/krb5.conf\n" +
                "canal.mq.kafka.kerberos.jaasFilePath = /data/cdcx/cdcx_manager/etc/kerberos/kafka_client_jaas.conf\n" +
                "canal.mq.properties.cdcx.schema.proxy.url = http://10.210.14.60:18080";

        PropertiesUtil util = new PropertiesUtil();
        Properties load = util.load(str);
        Object o = load.get("canal.mq.kafka.ddlTopic");
        System.out.println(o);
        System.out.println(load.getProperty("canal.mq.kafka.ddlTopic",""));

    }
}
