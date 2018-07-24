package com.cloud.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.cloud.user.entity.myenum.Level;
import com.cloud.user.entity.myenum.Source;
import com.cloud.user.entity.myenum.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lwt
 * @date 2018/7/22 11:55
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class User implements Serializable {
    private String userId;
    private String userName;
    private String passWord;
    @JSONField(serialize = false)
    private String salt;
    private String realName;
    private Integer sex;
    private String telPhone;
    private String email;
    private Integer level;
    private Integer status;
    private Integer source;
    private Date createTime;

    public User setLevel(Level level){
        this.level = level.getIndex();
        return this;
    }

    public User setSource(Source source){
        this.source = source.getIndex();
        return this;
    }

    public User setStatus(Status status){
        this.status = status.getIndex();
        return this;
    }
}
