package com.cloud.user.dao;

import com.cloud.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author lwt
 * @date 2018/7/22 12:08
 */
@Repository
public interface UserDao {
    /**
     * 插入用户
     * @param user 要注册的用户
     */
    void insertUser(User user);
    /**
     * 通过手机号查用户
     * @param telPhone 手机号
     * @return 查询到的用户，无此用户则返回null
     */
    User queryUserByTel(String telPhone);
}
