package com.howie.java.concurrency.thread;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-08-13
 * @Time 16:58
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(InterruptTest::run1);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    /**
     * 恢复中断状态以避免屏蔽中断
     */
    private static void run1() {
        int i = 1;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                i++;
                System.out.println(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //恢复中断状态以避免屏蔽中断
                Thread.currentThread().interrupt();
            }
        }
    }
}
