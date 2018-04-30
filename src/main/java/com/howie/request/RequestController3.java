package com.howie.request;

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
 * @Time 21:26
 */
@RestController
@RequestMapping("/requestController3")
public class RequestController3 extends BaseController {
    /**
     * 线程安全
     *
     * 分析：当创建不同的派生类对象时，基类中的域（这里是注入的request）在不同的派生类对象中会占据不同的内存空间，
     * 也就是说将注入request的代码放在基类中对线程安全性没有任何影响
     *
     * 避免了在不同的Controller中重复注入request, 只需要一个继承即可；
     * 但是考虑到java只允许继承一个基类，所以如果Controller需要继承其他类时，该方法便不再好用。
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return request.getRequestURI();
    }
}

/**
 * 方便展示，便写在同一个文件中
 */
class BaseController {
    @Autowired
    protected HttpServletRequest request;
}
