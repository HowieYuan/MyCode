package com.howie.java.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-04-21
 * @Time 21:30
 */
@RestController
@RequestMapping("/requestController4")
public class RequestController4 {
    /**
     * 线程安全
     *
     * 分析：本方法通过手动方法调用实现。因此本方法也是线程安全的。
     *
     * 优点：可以在非Bean中直接获取。缺点：如果使用的地方较多，代码非常繁琐；因此可以与其他方法配合使用。
     */
    @RequestMapping("/test")
    public String test() throws InterruptedException {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        return request.getRequestURI();
    }
}
