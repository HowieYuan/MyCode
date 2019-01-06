package com.howie.java.test;

import java.util.function.Function;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description  测量运行时间
 * @Date 2018-07-31
 * @Time 14:22
 */
public class Measure {
    public long measure(Function function, Object n) {
        long faster = Long.MAX_VALUE;
        long start = System.nanoTime();
        function.apply(n);
        long duration = (System.nanoTime() - start) / 1000000;
        if (duration < faster) {
            faster = duration;
        }
        return faster;
    }

}
