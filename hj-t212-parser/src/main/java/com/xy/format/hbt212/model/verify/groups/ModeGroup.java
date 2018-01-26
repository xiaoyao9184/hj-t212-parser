package com.xy.format.hbt212.model.verify.groups;

/**
 * 模式 验证组
 * Created by xiaoyao9184 on 2018/1/10.
 */
public interface ModeGroup {
    /**
     * 严格模式
     */
    @Deprecated
    interface Strict{}

    /**
     * 分包模式
     */
    interface UseSubPacket{}
}
