package com.cloud.user.entity.myenum;

/**
 * 来源枚举类
 *
 * @author lwt
 * @date 2018/7/22 14:20
 */
public enum Source {
    /**
     * 0 朋友圈
     * 1 朋友介绍
     * 2 公司
     * 3 其他
     */
    FriendCircle(0),
    FriendRecommend(1),
    Company(2),
    Other(3);
    private int index;

    Source(int index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
