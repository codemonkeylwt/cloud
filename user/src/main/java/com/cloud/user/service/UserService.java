package com.cloud.user.service;

import com.cloud.user.entity.User;

/**
 * @author lwt
 * @date 2018/7/22 12:22
 */
public interface UserService {
    /**
     * 用户注册service
     *
     * @param user 要注册的用户
     * @return user 要注册的用户
     */
    User signUpUser(User user);

    /**
     * 检查手机号是否已经注册
     *
     * @param telPhone 手机号
     * @return 手机号是否已注册
     */
    boolean checkTelphone(String telPhone);

    /**
     * 验证手机验证码
     *
     * @param key  redis中KV的key
     * @param code 输入的验证码
     * @return 验证码是否正确
     */
    boolean checkCode(String key, String code);

    /**
     * 通过手机号查用户
     *
     * @param telphone 手机号
     * @return 查询到的用户
     */
    User getUserByTel(String telphone);
}
