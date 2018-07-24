package com.cloud.user.entity.myenum;

/**
 * 级别常量枚举
 *
 * @author lwt
 * @date 2018/7/22 12:28
 */
public enum Level {
    /**
     * 等级1,2,3,4,5
     */
    ONE(1),
    TOW(2),
    THERE(3),
    FOUR(4),
    FIVE(5);
    private int index;

    Level(int index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
