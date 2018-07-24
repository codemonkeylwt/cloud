package com.cloud.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.user.entity.User;
import com.cloud.user.service.UserService;
import com.cloud.user.util.ExceptionUtil;
import com.cloud.user.util.JsonResult;
import com.cloud.user.util.RedisUtil;
import com.cloud.user.util.SmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lwt
 * @date 2018/7/22 11:11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final RedisUtil redisUtil;
    @Autowired
    private UserController(UserService userService, RedisUtil redisUtil) {
        this.userService = userService;
        this.redisUtil = redisUtil;
    }

    /**
     * 用户注册接口
     * @param user 表单传来的新用户
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public JsonResult signUp(User user, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        User user2;
        try {
            user2 = userService.signUpUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("userID", JSONObject.toJSONString(user2));
            logger.info("用户注册成功[{}]", user2);
        } catch (Exception e) {
            jsonResult.setSuccess(false).setMsg("系统错误！");
            logger.error("用户注册失败[{}]", ExceptionUtil.getExceptionInfo(e));
            return jsonResult;
        }
        return jsonResult;
    }

    /**
     * 发送手机验证码接口
     * @param telPhone 手机号
     */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    public JsonResult sendSms(String telPhone){
        JsonResult jsonResult = new JsonResult();
        try {
            if (userService.checkTelphone(telPhone)) {
                JsonResult smsResult = SmsUtil.sendSms(telPhone);
                if(smsResult.isSuccess()){
                    String code = (String)smsResult.getResult();
                    code = code.toLowerCase();
                    redisUtil.set(telPhone,code,300);
                    logger.info("短信发送成功[{}]", telPhone);
                    return jsonResult;
                }else {
                    return jsonResult.setSuccess(false).setMsg("短信发送失败!");
                }
            }else {
                return jsonResult.setSuccess(false).setMsg("手机已被注册！");
            }
        }catch (Exception e){
            logger.error("短信发送失败[{}]", ExceptionUtil.getExceptionInfo(e));
            return jsonResult.setSuccess(false).setMsg("短信发送失败！");
        }
    }

    /**
     * 验证手机验证码接口
     * @param telPhone 手机号
     * @param code 输入的验证码
     */
    @RequestMapping(value = "/checkTel", method = RequestMethod.POST)
    public JsonResult checkTel(String code,String telPhone) {
        JsonResult jsonResult = new JsonResult();
        if (code==null){
            return jsonResult.setSuccess(false).setMsg("验证码错误！");
        }
        code = code.toLowerCase();
        String s = redisUtil.get(telPhone);
        if (code.equals(s)){
            return jsonResult;
        }else {
            return jsonResult.setSuccess(false).setMsg("验证码错误！");
        }
    }
}
