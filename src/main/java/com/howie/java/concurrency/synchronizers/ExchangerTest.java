package com.howie.java.concurrency.synchronizers;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-08-08
 * @Time 20:52
 */
public class ExchangerTest {
    /*
    用于交换各个线程的数据，在各方的栅栏位置交换数据（得等各个线程的数据都做好准备才行。）
     */
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Exchanger<String> exchanger = new Exchanger<>();
        service.execute(() -> {
            try {
                String data1 = "111111";
                System.out.println("线程" + Thread.currentThread().getName() +
                        "正在把数据" + data1 + "换出去");
                Thread.sleep((long) (Math.random() * 10000));
                String data2 = exchanger.exchange(data1);
                System.out.println("线程" + Thread.currentThread().getName() +
                        "换回的数据为" + data2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            try {
                String data1 = "222222";
                System.out.println("线程" + Thread.currentThread().getName() +
                        "正在把数据" + data1 + "换出去");
                Thread.sleep((long) (Math.random() * 10000));
                String data2 = exchanger.exchange(data1);
                System.out.println("线程" + Thread.currentThread().getName() +
                        "换回的数据为" + data2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
