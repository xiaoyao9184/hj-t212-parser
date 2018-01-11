package com.xy.format.segment.base.cfger;

/**
 * Created by xiaoyao9184 on 2018/1/9.
 */
public interface Configurator<Target> {

    /**
     * 配置
     * @param target 配置目标
     */
    void config(Target target);
}
