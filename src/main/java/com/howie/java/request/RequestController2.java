package com.howie.java.request;

import org.springframework.beans.factory.annotation.Autowired;
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
 * @Time 21:14
 */
@RestController
@RequestMapping("/requestController2")
public class RequestController2 {
    /**
     * 线程安全
     *
     * 使用这种方式，当Bean（RequestController2）初始化时，Spring并没有注入一个request对象，
     * （所以 idea 报了 “Could not autowire. No beans of 'HttpServletRequest' type found.”）
     * 而是注入了一个代理（proxy）；当Bean中需要使用request对象时，通过该代理获取request对象。
     *
     * 但是，web系统中有很多controller，每个controller中都会使用request对象（这种场景实际上非常频繁），
     * 这时每个类都需要注入，就需要写很多次注入request的代码；如果还需要注入response，代码就更繁琐了。
     */
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return request.getRequestURI();
    }
}
