package com.howie.java.request;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-04-21
 * @Time 21:33
 */
@RestController
@RequestMapping("/requestController5")
public class RequestController5 {
    private HttpServletRequest request;

    /**
     * 非线程安全
     *
     * 分析：@ModelAttribute 注解用在Controller中修饰方法时，
     * 其作用是Controller中的每个@RequestMapping方法执行前，该方法都会执行。
     * 因此在本例中，bindRequest()的作用是在test()执行前为request对象赋值。
     * 虽然bindRequest()中的参数request本身是线程安全的，
     * 但由于TestController是单例的，request作为TestController的一个域，无法保证线程安全。
     */
    @ModelAttribute
    public void bindRequest(HttpServletRequest request) {
        this.request = request;

    }

    @RequestMapping("/test")
    public String test() throws InterruptedException {
        return request.getRequestURI();
    }
}
