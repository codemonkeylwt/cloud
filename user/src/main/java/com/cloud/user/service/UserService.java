package com.cloud.user.service;

import com.cloud.user.entity.User;

/**
 * @author lwt
 * @date 2018/7/22 12:22
 */
public interface UserService {
    /**
     * 用户注册service
     * @param user 要注册的用户
     * @return user 要注册的用户
     */
    User signUpUser(User user);
    /**
     * 检查手机号
     * @param telPhone 手机号
     * @return 手机号是否已注册
     */
    boolean checkTelphone(String telPhone);
}
