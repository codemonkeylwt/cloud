package com.cloud.user.service;

import com.cloud.user.dao.UserDao;
import com.cloud.user.entity.myenum.Source;
import com.cloud.user.entity.myenum.Level;
import com.cloud.user.entity.myenum.Status;
import com.cloud.user.entity.User;
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

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User signUpUser(User user) {
        user.setLevel(Level.ONE).setCreateTime(new Date()).setStatus(Status.Normal);
        user.setPassWord(UserSignUpUtil.getHexPassword(user.getPassWord())).setSalt(UserSignUpUtil.getSalt());
        user.setUserId(UserSignUpUtil.getId()).setSource(Source.FriendCircle);
        userDao.insertUser(user);
        return user;
    }

    @Override
    public boolean checkTelphone(String telPhone){
        return userDao.queryUserByTel(telPhone)==null;
    }
}
