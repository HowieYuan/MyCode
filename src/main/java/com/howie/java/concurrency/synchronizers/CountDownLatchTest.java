package com.howie.java.concurrency.synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-08-08
 * @Time 20:38
 */
public class CountDownLatchTest {
    /*
    使一个或多个线程等待一组事件的完成，

    使用 await 方法将线程挡在闭锁前，等待计数器达到零，
    使用 countDown 方法递减计数器
     */
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = () -> {
                try {
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "正准备接受命令");
                    cdOrder.await();
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "已接受命令");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "回应命令处理结果");
                    cdAnswer.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            service.execute(runnable);
        }

        try {
            Thread.sleep((long) (Math.random() * 10000));

            System.out.println("线程" + Thread.currentThread().getName() +
                    "即将发布命令");
            cdOrder.countDown();
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已发送命令，正在等待结果");
            cdAnswer.await();
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已收到所有响应结果");
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
