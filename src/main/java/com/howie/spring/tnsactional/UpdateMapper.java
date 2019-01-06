package com.howie.spring.tnsactional;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-06-02
 * @Time 22:39
 */
@Repository
public interface UpdateMapper {
    @Update("update user set user = #{i} where id = 1")
    void updateUser(int i);

    @Select("select user from user where id = 1")
    int getUser();
}
