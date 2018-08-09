package com.howie.concurrency.synchronizers;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-08-08
 * @Time 20:30s
 */
public class CyclicBarrierTest {
    /*
    它通过构造函数指定一个线程数 n, 能做到让 n 个线程互相等待,当 n 个线程都做到某一步后,再继续下一步。

    当有线程到达时调用 await，说明有一个线程已到达，当所有线程都到达，则释放，重置栅栏
     */
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = () -> {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将到达集合地点1，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                    cb.await();

                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将到达集合地点2，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                    cb.await();
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}

