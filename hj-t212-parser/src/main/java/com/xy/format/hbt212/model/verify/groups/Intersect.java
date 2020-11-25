package com.xy.format.hbt212.model.verify.groups;

import java.util.Arrays;
import java.util.List;

/**
 * 验证组 交集
 *
 * 无法使用group sequence解决 https://stackoverflow.com/questions/9741092/jsr-bean-validation-and-multiple-groups-and-vs-or
 * Created by xiaoyao9184 on 2020/11/25.
 */
public interface Intersect {

    /**
     * 分包 && 版本
     */
    interface SubPacket {
        interface Version {
            interface V2005 {}
            interface V2017 {}
        }
    }

    /**
     * 自动添加交集组
     * @param groups
     */
    static void intersect(List<Class> groups) {
        if(groups.containsAll(Arrays.asList(
                Group.SubPack.class,
                Group.Version.V2005.class))){
            groups.add(SubPacket.Version.V2005.class);
        }
        if(groups.containsAll(Arrays.asList(
                Group.SubPack.class,
                Group.Version.V2017.class))){
            groups.add(SubPacket.Version.V2017.class);
        }
    }
}