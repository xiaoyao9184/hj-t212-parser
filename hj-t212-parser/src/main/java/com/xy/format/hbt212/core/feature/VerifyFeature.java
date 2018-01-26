package com.xy.format.hbt212.core.feature;

import com.xy.format.segment.base.cfger.Feature;

/**
 * 验证特性
 * Created by xiaoyao9184 on 2018/1/3.
 */
public enum VerifyFeature implements Feature {

    /**
     * 数据区长度
     */
    DATA_LEN_RANGE(true),

    /**
     * 数据区CRC
     */
    DATA_CRC(false),

    /**
     * 允许字段丢失
     */
    @Deprecated
    ALLOW_MISSING_FIELD(false),

    /**
     * 允许值不在范围
     */
    @Deprecated
    ALLOW_VALUE_NOT_IN_RANGE(false),

    /**
     * 启动校验
     */
    USE_VERIFICATION(true),

    /**
     * 校验失败报错
     */
    THROW_ERROR_VERIFICATION_FAILED(true),

    /**
     * 严格模式
     */
    @Deprecated
    STRICT(false);


    private final boolean _defaultState;
    private final int _mask;

    VerifyFeature(boolean defaultState) {
        _defaultState = defaultState;
        _mask = (1 << ordinal());
    }

    @Override
    public boolean enabledByDefault() { return _defaultState; }

    @Override
    public int getMask() { return _mask; }

    @Override
    public boolean enabledIn(int flags) { return (flags & _mask) != 0; }

}
