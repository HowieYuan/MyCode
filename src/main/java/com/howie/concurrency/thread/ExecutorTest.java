package com.howie.concurrency.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-08-10
 * @Time 23:52
 */
public class ExecutorTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new ExecutorTest().futureTimeTest();
    }

    /**
     * Future 接口 + Callable 接口
     */
    public void futureTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "callable!";
        };
        System.out.println("here1");
        Future<String> future = executorService.submit(callable);
        System.out.println("here2");
        String result = future.get();
        System.out.println(result);
    }

    /**
     * 指定时间获得信息，否则取默认值
     */
    public void futureTimeTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "callable!";
        };
        System.out.println("here1");
        Future<String> future = executorService.submit(callable);
        System.out.println("here2");
        String result;
        try {
            result = future.get(1000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            result = "超时了，这是默认值";
        }
        System.out.println(result);
        System.gc();
        executorService.shutdown();
    }

    /**
     * CompletionService 接口
     */
    public void completionServiceTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        for (String s : list) {
            completionService.submit(() -> {
                System.out.println("here1");
                Thread.sleep(10000);
                return s + " task";
            });
        }
        for (String ignored : list) {
            System.out.println("here2");
            Future<String> future = completionService.take();
            System.out.println("here3");
            String result = future.get();
            System.out.println(result);
        }
    }
}


