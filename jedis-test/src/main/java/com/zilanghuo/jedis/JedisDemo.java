package com.zilanghuo.jedis;

import cn.hutool.core.bean.BeanUtil;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author laiwufa
 * @date 2019/5/16 0016 下午 4:32
 */
public class JedisDemo {

    public static String key = "cacheCount";

    public static String hashKey = "product";

    public static String listKey = "011111";

    public static String setKey = "liebiao";

    public static String setKey2 = "liebiao2";

    public static String sortedKey = "sorted";

    public static void main(String[] args) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("172.17.34.209", 7002));
        jedisClusterNodes.add(new HostAndPort("172.17.34.209", 7000));
        JedisCluster jedis = new JedisCluster(jedisClusterNodes);
        //stringTypeDemo(jedis);
        //hashTypeDemo(jedis);
        sortedTypeDemo(jedis);
    }

    /**
     * string类型：常规计数，微博数
     *
     * @param jedis
     */
    static void stringTypeDemo(JedisCluster jedis) {
        jedis.set(key, "100");
        System.out.println(jedis.get(key));
    }

    /**
     * hash类型，适用于存储对象，一个产品的信息
     *
     * @param jedis
     */
    static void hashTypeDemo(JedisCluster jedis) {
        Product product = new Product();
        product.setId(2);
        product.setName("产品编号1");
        product.setAmount(10);
        product.setDescript("互联网金融");
        Map<String, Object> map = BeanUtil.beanToMap(product);
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            jedis.hset(hashKey, next.getKey(), next.getValue() + "");
        }
        // 获取模块
        Map<String, String> resultMap = jedis.hgetAll(hashKey);
        Iterator<Map.Entry<String, String>> resultIte = resultMap.entrySet().iterator();
        while (resultIte.hasNext()) {
            Map.Entry<String, String> next = resultIte.next();
            System.out.println("key:" + next.getKey() + ",value:" + next.getValue());
        }
    }

    /**
     * list模式，粉丝列表，关注列表，消息列表,包含重复的数据
     *
     * @param jedis
     */
    static void listTypeDemo(JedisCluster jedis) {
        jedis.rpush(listKey, "a");
        jedis.rpush(listKey, "a");
        jedis.rpush(listKey, "b");
        jedis.rpush(listKey, "c");
        jedis.rpush(listKey, "d");
        System.out.println("listLenght:" + jedis.llen(listKey));
        System.out.println(jedis.rpop(listKey));// last元素
        System.out.println(jedis.lrange(listKey,1,5));
    }

    /**
     * set模式，去重
     * @param jedis
     */
    static void setTypeDemo(JedisCluster jedis){
        jedis.sadd(setKey,"a");
        jedis.sadd(setKey,"a");
        jedis.sadd(setKey,"b");
        jedis.sadd(setKey,"c");
        jedis.sadd(setKey,"d");

        jedis.sadd(setKey2,"a");
        jedis.sadd(setKey2,"b");
        jedis.sadd(setKey2,"e");
        jedis.sadd(setKey2,"f");
        //交集
        //System.out.println(jedis.sinter(setKey,setKey2));
        System.out.println(jedis.sdiff(setKey,setKey2));
    }

    /**
     * sorted,排序
     * @param jedis
     */
    static void sortedTypeDemo(JedisCluster jedis){
        jedis.zadd(sortedKey,10,"a");
        jedis.zadd(sortedKey,13,"b");
        jedis.zadd(sortedKey,12,"c");
        System.out.println(jedis.zrange(sortedKey,0,2));
    }

}
