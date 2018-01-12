package com.xy.format.hbt212.model.verify;

import com.xy.format.hbt212.model.verify.groups.TypeGroup;

/**
 * 数据段 元素
 * Created by xiaoyao9184 on 2017/12/19.
 */
public enum DataElement {
    QN(TypeGroup.YYYYMMDDhhmmsszzz.class),
    PNUM(DataElement.Group.PNUM.class,false),
    PNO(DataElement.Group.PNO.class,false),
    ST(DataElement.Group.ST.class),
    CN(DataElement.Group.CN.class),
    PW(DataElement.Group.PW.class),
    MN(DataElement.Group.MN.class),
    Flag(DataElement.Group.Flag.class),
    CP(DataElement.Group.CP.class);

    private Class group;
    private boolean required;

    DataElement(Class group){
        this.group = group;
        this.required = true;
    }

    DataElement(Class group, boolean required){
        this.group = group;
        this.required = required;
    }


    public Class group() {
        return group;
    }

    public boolean isRequired() {
        return required;
    }

    public interface Group {
        interface QN{}
        interface PNUM{}
        interface PNO{}
        interface ST{}
        interface CN{}
        interface PW{}
        interface MN{}
        interface Flag{}
        interface CP{}
    }

}
