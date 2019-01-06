package com.howie.java.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-04-21
 * @Time 20:25
 */
@RestController
@RequestMapping("/requestController1")
public class RequestController1 {
    /**
     * 线程安全
     *
     * 缺点：request对象的获取只能从controller开始，如果使用request对象的地方在函数调用层级比较深的地方，
     * 那么整个调用链上的所有方法都需要添加request参数
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
