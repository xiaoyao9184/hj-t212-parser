package com.xy.format.segment.base.cfger;

/**
 * Created by xiaoyao9184 on 2018/1/9.
 */
public interface Configured<Target> {

    /**
     * 被配置
     * @param by 目标配置器
     */
    void configured(Configurator<Target> by);
}
