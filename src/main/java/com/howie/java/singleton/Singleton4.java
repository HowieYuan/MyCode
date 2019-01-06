package com.howie.java.singleton;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-09-13
 * @Time 20:59
 */
public class Singleton4 {
    private static volatile Singleton4 singleton;

    private Singleton4() {

    }

    public synchronized Singleton4 getInstance() {
        if (singleton == null) {
            synchronized (Singleton4.class) {
                if (singleton == null) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}