package com.howie.tnsactional;

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
        updateMapper.updateUser();
        throw new Exception("事务回滚");
    }
}
