package com.xy.format.hbt212.core.feature;

import com.xy.format.segment.base.cfger.Feature;

/**
 * 生成特性
 * Created by xiaoyao9184 on 2018/2/24.
 */
public enum GeneratorFeature implements Feature {

    /**
     * 未使用
     */
    UN_USE(true);


    private final boolean _defaultState;
    private final int _mask;

    GeneratorFeature(boolean defaultState) {
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
