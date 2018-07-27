package com.cloud.user.service;

import com.cloud.user.dao.UserDao;
import com.cloud.user.entity.myenum.Source;
import com.cloud.user.entity.myenum.Level;
import com.cloud.user.entity.myenum.Status;
import com.cloud.user.entity.User;
import com.cloud.user.util.RedisUtil;
import com.cloud.user.util.UserSignUpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lwt
 * @date 2018/7/22 12:26
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RedisUtil redisUtil;

    @Autowired
    public UserServiceImpl(UserDao userDao, RedisUtil redisUtil) {
        this.userDao = userDao;
        this.redisUtil = redisUtil;
    }

    @Override
    public User signUpUser(User user) {
        user.level(Level.ONE).setCreateTime(new Date()).status(Status.Normal);
        user.setPassWord(UserSignUpUtil.getHexPassword(user.getPassWord())).setSalt(UserSignUpUtil.getSalt());
        user.setUserId(UserSignUpUtil.getId()).source(Source.FriendCircle);
        userDao.insertUser(user);
        return user;
    }

    @Override
    public boolean checkTelphone(String telPhone) {
        return userDao.queryUserByTel(telPhone) == null;
    }

    @Override
    public boolean checkCode(String key, String code) {
        code = code.toLowerCase();
        String s = redisUtil.get(key);
        boolean equals = code.equals(s);
        if (equals){
            redisUtil.del(key);
        }
        return equals;
    }

    @Override
    public User getUserByTel(String telphone) {
        return userDao.queryUserByTel(telphone);
    }
}
