package com.xy.format.segment.core.feature;

/**
 * Created by xiaoyao9184 on 2018/1/3.
 */
public interface Feature {

    boolean enabledByDefault();

    int getMask();

    boolean enabledIn(int flags);
}
