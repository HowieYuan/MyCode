package com.howie.java.singleton;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-09-13
 * @Time 20:56
 */
public class Singleton2 {
    private static Singleton2 singleton;

    private Singleton2() {

    }

    public Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}
