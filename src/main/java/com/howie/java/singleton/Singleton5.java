package com.howie.java.singleton;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-09-13
 * @Time 21:13
 */
public class Singleton5 {
    private Singleton5() {

    }

    private static class InnerClass {
        private final static Singleton5 SINGLETON = new Singleton5();
    }

    public Singleton5 getInstance() {
        return InnerClass.SINGLETON;
    }
}
