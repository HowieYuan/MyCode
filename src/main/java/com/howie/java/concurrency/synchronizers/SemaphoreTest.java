package com.howie.java.concurrency.synchronizers;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 同步工具类：Semaphore 信号量
 * @Date 2018-08-08
 * @Time 19:28
 */
public class SemaphoreTest {
    /*
    控制同时访问某个特定资源的操作数量，它管理着一组虚拟的通过构造函数来指定数量的许可（permit）

    使用时获得许可```semaphore.acquire();```，完成后释放许可```semaphore.release();```；
    没有许可时， acquire 方法将阻塞直到有许可

    - 实现资源池：构造固定长度的资源池，当池为空，呈阻塞状态而不是返回失败状态
    - 构造有界阻塞容器：添加元素前获取一个许可，如果已满，则阻塞直到许可被释放；当移除某元素，释放一个许可
     */
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(3);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() +
                        "进入，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
                System.out.println("线程" + Thread.currentThread().getName() +
                        "进入，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() +
                        "即将离开");
                semaphore.release();
                //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
                System.out.println("线程" + Thread.currentThread().getName() +
                        "已离开，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
            };
            service.execute(runnable);
        }

    }
}
