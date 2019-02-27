package com.zilanghuo.zk;

import org.apache.zookeeper.*;

/**
 * @author laiwufa
 * @date 2019/2/26 0026 上午 11:32
 */
public class CreateNodeService {


    public static void main(String[] args) throws Exception {
        String ip = "172.17.34.121:2181";
        ZooKeeper zk = new ZooKeeper(ip, 1000, new CreateWatcher());
        for (int i = 0; i < 10; i++) {
//            createNode(zk,"/local/persistentSeq", CreateMode.PERSISTENT_SEQUENTIAL);
        }
        for (int i = 0; i < 2; i++) {
            createNode(zk, "/local/ephemeralSequential", CreateMode.EPHEMERAL);
        }

        Thread.sleep(1000 * 2); // 等待20秒关闭ZooKeeper连接
        zk.close(); // 关闭连接后创建的临时节点将自动删除

    }


    private static void createNode(ZooKeeper zk, String path, CreateMode ephemeralSequential) throws KeeperException, InterruptedException {
        String createdPath = zk.create(path, path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, ephemeralSequential);
        System.out.println("createdPath = " + createdPath);
    }

}

class CreateWatcher implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("监听到数据:" + watchedEvent.getState());
    }
}