package com.howie.spring.tnsactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 事务管理测试
 * @Date 2018-06-02
 * @Time 21:38
 */
@RestController
public class TnsactionalTest {
    @Autowired
    private UpdateMapper updateMapper;

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("tnsactionalTest")
    public int tnsactionalTest() throws Exception {
        System.out.println("test1 ： " + updateMapper.getUser());
        updateMapper.updateUser(222);
        System.out.println("test1 ： " + updateMapper.getUser());
        Thread.sleep(20000);
//        throw new Exception(updateMapper.getUser() + "");
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("tnsactionalTest2")
    public int tnsactionalTest2() throws Exception {
        System.out.println("test2 ： " + updateMapper.getUser());
        updateMapper.updateUser(333);
        System.out.println("test2 ： " + updateMapper.getUser());
        Thread.sleep(20000);
//        throw new Exception(updateMapper.getUser() + "");
        return 2;
    }
}
