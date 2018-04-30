package com.howie.publicPackage.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 接口返回对象
 * @Date 2018-03-17
 * @Time 22:25
 */
@Component
public class ResultMap extends HashMap<String, Object> {
    public ResultMap() {
    }

    public ResultMap success() {
        this.put("code", 200);
        return this;
    }

    public ResultMap fail(int code) {
        this.put("code", code);
        return this;
    }

    public ResultMap message(String message) {
        this.put("message", message);
        return this;
    }

    public ResultMap data(Object data) {
        this.put("data", data);
        return this;
    }
}

