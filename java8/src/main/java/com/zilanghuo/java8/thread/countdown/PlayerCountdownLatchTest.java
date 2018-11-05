package com.zilanghuo.java8.thread.countdown;

import java.util.concurrent.*;

/**
 * @author laiwufa
 * @date 2018/11/5
 * use:跑步代码测试
 * 一个跑步比赛，有五个选手参加，有两点需要注意，
 * 第一：我们必须确保这5个选手都准备就绪了，才能宣布比赛开始
 * 第二：只有当5个选手都完成比赛了才能宣布比赛结束
 */
public class PlayerCountdownLatchTest {

    private static final int PLAYER_COUNT = 5;

    public static void main(String[] args) {

        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);

        Player[] players = new Player[5];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players[i] = new Player(i + 1, begin, end);
        }
        ExecutorService executorService = new ThreadPoolExecutor(PLAYER_COUNT, PLAYER_COUNT, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        for (Player p : players) {
            executorService.execute(p);
        }
        System.out.println("race begin:");
        //线程都放入线程池，相当于初始化完毕，准备就绪
        begin.countDown();
        try {
            // 只有当end为0时，宣布比赛结束
            end.await();
        } catch (InterruptedException e) {

        } finally {
            System.out.println("race end!");
        }
        executorService.shutdown();

    }


}

class Player implements Runnable {

    private int id;
    private CountDownLatch begin;
    private CountDownLatch end;

    public Player(int i, CountDownLatch begin, CountDownLatch end) {
        this.id = i;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            //等待begin的状态为0，这里表示等待其他选手就绪，然后一起开始比赛
            begin.await();
            //随机分配时间，即运动员完成时间
            Thread.sleep((long) (Math.random() * 100) * 100);
            //选手到达终点
            System.out.println("Play" + id + " arrived.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //使end状态减1，最终减至0，为0时比赛结束
            end.countDown();
        }
    }
}