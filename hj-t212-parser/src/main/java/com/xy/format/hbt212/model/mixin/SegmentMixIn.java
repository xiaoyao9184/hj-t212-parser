package com.xy.format.hbt212.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by xiaoyao9184 on 2017/12/19.
 */
public abstract class SegmentMixIn {

    @JsonIgnore
    abstract String getCp();

    @JsonIgnore
    abstract void setCp(String cp);
}
