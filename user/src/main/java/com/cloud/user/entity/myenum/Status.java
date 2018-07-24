package com.cloud.user.entity.myenum;

/**
 * 用户状态枚举
 * @author lwt
 * @date 2018/7/22 14:26
 */
public enum  Status {
    /**
     * 0 邮箱未激活
     * 1 正常
     * 2 冻结
     */
    Unactivation(0),
    Normal(1),
    Freeze(2);
    private int index;
    Status(int index){
        this.index = index;
    }
    public int getIndex(){
        return index;
    }
}
