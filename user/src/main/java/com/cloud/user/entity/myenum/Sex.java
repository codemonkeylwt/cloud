package com.cloud.user.entity.myenum;

/**
 * @author lwt
 * @date 2018/7/22 16:27
 */
public enum Sex {
    /**
     * 0 男
     * 1 女
     */
    MALE(0),
    FEMALE(1);
    private int index;

    Sex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
