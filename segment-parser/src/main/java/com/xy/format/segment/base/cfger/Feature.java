package com.xy.format.segment.base.cfger;

/**
 * Created by xiaoyao9184 on 2018/1/3.
 */
public interface Feature {

    boolean enabledByDefault();

    int getMask();

    boolean enabledIn(int flags);


    static <F extends Enum<F> & Feature> int collectFeatureDefaults(Class<F> enumClass) {
        int flags = 0;
        for (F value : enumClass.getEnumConstants()) {
            if (value.enabledByDefault()) {
                flags |= value.getMask();
            }
        }
        return flags;
    }
}
