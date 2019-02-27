package com.zilanghuo.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author laiwufa
 * @date 2019/2/27 0027 下午 4:11
 */
public class PublishWatcherTest {

    static CuratorFramework client = null;
    static final String PATH = "/local/persistentSeq0000000087";
    static final String zkAddress = "172.17.34.121:2181";
    static final int timeout = 10000;
    static CountDownLatch countDownLatch = new CountDownLatch(1);


    /**
     * 初始化，增加客户端监听器
     * @throws Exception
     */
    public static void init() throws Exception {
        client = CuratorFrameworkFactory.builder().connectString(zkAddress)
                .sessionTimeoutMs(timeout).retryPolicy(new RetryNTimes(5, 5000)).build();
        // 客户端注册监听，进行连接配置
        client.getConnectionStateListenable().addListener(new ClientConnectionStateListener());
        client.start();
        // 连接成功后，才进行下一步的操作
        countDownLatch.await();
    }

    /**
     * 重新初始化
     */
    public static void reinit() {
        try {
            unregister();
            init();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 关闭连接
     */
    public static void unregister() {
        try {
            if (client != null) {
                client.close();
                client = null;
            }
        } catch (Exception e) {
            System.out.println("unregister failed");
        }
    }

    // 对path进行监听配置
    public static String watcherPath(String path, CuratorWatcher watcher) throws Exception {
        byte[] buffer = client.getData().usingWatcher(watcher).forPath(path);
        System.out.println(new String(buffer));
        return new String(buffer);
    }

    public static String readPath(String path) throws Exception {
        byte[] buffer = client.getData().forPath(path);
        return new String(buffer);

    }

    public static void main(String[] args) throws Exception {
        init();
        watcherPath(PATH, new NodeWatcher());
        Thread.sleep(Integer.MAX_VALUE);
    }


    /**
     * 创建客户端监听器
     */
    static class ClientConnectionStateListener implements ConnectionStateListener {
        @Override
        public void stateChanged(CuratorFramework client, ConnectionState newState) {
            if (newState == ConnectionState.CONNECTED) {
                System.out.println("connected established");
                countDownLatch.countDown();
            } else if (newState == ConnectionState.LOST) {
                System.out.println("connection lost,waiting for reconection");
                try {
                    System.out.println("reinit---");
                    reinit();
                    System.out.println("inited---");
                } catch (Exception e) {
                    System.out.println("re-inited failed");
                }
            }
        }
    }

    /**
     * 创建节点监听器
     */
    static class NodeWatcher implements CuratorWatcher{

        @Override
        public void process(WatchedEvent event) throws Exception {
            // 当数据变化后，重新获取数据信息
            if (event.getType() == Watcher.Event.EventType.NodeDataChanged) {
                //获取更改后的数据，进行相应的业务处理
                String value = readPath(event.getPath());
                System.out.println(value);
            }
        }
    }
}
