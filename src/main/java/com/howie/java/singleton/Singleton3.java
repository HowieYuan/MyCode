package com.howie.java.singleton;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-09-13
 * @Time 20:58
 */
public class Singleton3 {
    private static Singleton3 singleton;

    private Singleton3() {

    }

    public synchronized Singleton3 getInstance() {
        if (singleton == null) {
            singleton = new Singleton3();
        }
        return singleton;
    }
}
