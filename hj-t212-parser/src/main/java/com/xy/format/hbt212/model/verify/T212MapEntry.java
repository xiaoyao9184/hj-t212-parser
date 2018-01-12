package com.xy.format.hbt212.model.verify;

import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.model.verify.groups.TypeGroup;

import javax.validation.constraints.*;

/**
 * T212 Map
 * 解决无法对MAP进行验证定义问题
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212MapEntry {

    private String key;

    @NotNull(groups = {
            DataElement.Group.QN.class,
            DataElement.Group.PNUM.class,
            DataElement.Group.PNO.class,
            DataElement.Group.ST.class,
            DataElement.Group.CN.class,
            DataElement.Group.PW.class,
            DataElement.Group.MN.class,
            DataElement.Group.Flag.class
    })
    @ValidDate(format = "yyyyMMddHHmmssSSS", groups = DataElement.Group.QN.class)
    @Max(value = 4, groups = DataElement.Group.PNUM.class)
    @Max(value = 4, groups = DataElement.Group.PNO.class)
    @Max(value = 2, groups = DataElement.Group.ST.class)
    @Max(value = 4, groups = DataElement.Group.CN.class)
    @Max(value = 6, groups = DataElement.Group.PW.class)
    @Max(value = 14, groups = DataElement.Group.MN.class)
    @Max(value = 3, groups = DataElement.Group.Flag.class)
    @Min(value = 1, groups = DataElement.Group.Flag.class)
    @Max(value = 960, groups = DataElement.Group.CP.class)

    @ValidDate(format = "yyyyMMddHHmmss", groups = TypeGroup.YYYYMMDDhhmmss.class)
    @ValidDate(format = "HHmmss", groups = TypeGroup.hhmmss.class)
    @N(integer = 1, groups = TypeGroup.N1.class)
    @N(integer = 2, groups = TypeGroup.N2.class)
    @N(integer = 3, groups = TypeGroup.N3.class)
    @N(integer = 4, groups = TypeGroup.N4.class)
    @N(integer = 14, groups = TypeGroup.N14.class)
    @N(integer = 2, fraction = 2, groups = TypeGroup.N2_2.class)
    @N(integer = 3, fraction = 1, groups = TypeGroup.N3_1.class)
    @C(len = 1, groups = TypeGroup.C1.class)
    @C(len = 4, groups = TypeGroup.C4.class)
    @C(len = 6, groups = TypeGroup.C6.class)
    @C(len = 24, groups = TypeGroup.C24.class)

    private String value;

    public T212MapEntry(String key, String value){
        this.key = key;
        this.value = value;
    }



    public static T212MapEntry of(String key, String value){
        return new T212MapEntry(key,value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
