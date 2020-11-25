package com.xy.format.hbt212.model.expand;

import com.xy.format.hbt212.core.validator.clazz.FieldC;
import com.xy.format.hbt212.core.validator.clazz.FieldN;
import com.xy.format.hbt212.core.validator.clazz.FieldValidDate;
import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.model.verify.groups.Group;
import com.xy.format.hbt212.model.verify.groups.Intersect;

import java.util.Map;

/**
 * T212 Map
 * 解决无法对MAP进行验证定义问题
 * Created by xiaoyao9184 on 2018/1/10.
 */
@FieldValidDate(field = "QN",
        value = @ValidDate(format = "yyyyMMddHHmmssSSS"))

//@FieldC(field = "ST", groups = Group.Strict.class,
//        value = @C(len = 2))
@FieldC(field = "ST", groups = Group.Version.V2005.class,
        value = @C(len = 5))
@FieldC(field = "ST", groups = Group.Version.V2017.class,
        value = @C(len = 5))

//@FieldC(field = "CN", groups = Group.Strict.class,
//        value = @C(len = 4))
@FieldC(field = "CN", groups = Group.Version.V2005.class,
        value = @C(len = 7))
@FieldC(field = "CN", groups = Group.Version.V2017.class,
        value = @C(len = 7))

@FieldC(field = "PW", groups = Group.Version.V2005.class,
        value = @C(len = 6))
@FieldC(field = "PW", groups = Group.Version.V2017.class,
        value = @C(len = 9))

@FieldC(field = "MN", groups = Group.Version.V2005.class,
        value = @C(len = 14))
@FieldC(field = "MN", groups = Group.Version.V2017.class,
        value = @C(len = 24))

@FieldN(field = "Flag",
        value = @N(integer = 3, max = 255, min = 0))

//TODO 这种写法的含义是 拆包&2005版本，拆包&2017版本
@FieldN(field = "PNUM", groups = Intersect.SubPacket.Version.V2005.class,
        value = @N(integer = 4, optional = false))
@FieldN(field = "PNUM", groups = Intersect.SubPacket.Version.V2017.class,
        value = @N(integer = 9, optional = false))
@FieldN(field = "PNO", groups = Intersect.SubPacket.Version.V2005.class,
        value = @N(integer = 4, optional = false))
@FieldN(field = "PNO", groups = Intersect.SubPacket.Version.V2017.class,
        value = @N(integer = 8, optional = false))
//本意为组取交，无法实现，目前无法实现
//@FieldN(field = "PNUM", groups = {Group.SubPack.class,Group.Version.V2005.class},
//        value = @N(integer = 4, optional = false))
//@FieldN(field = "PNUM", groups = {Group.SubPack.class,Group.Version.V2005.class},
//        value = @N(integer = 9, optional = false))
//@FieldN(field = "PNO", groups = {Group.SubPack.class,Group.Version.V2005.class},
//        value = @N(integer = 4, optional = false))
//@FieldN(field = "PNO", groups = {Group.SubPack.class,Group.Version.V2017.class},
//        value = @N(integer = 8, optional = false))
@FieldC(field = "CP", groups = Group.Version.V2005.class,
        value = @C(len = 960))
@FieldC(field = "CP", groups = Group.Version.V2017.class,
        value = @C(len = 950))
public class T212DataLevelMap
        extends T212Map<String,String> {

    public T212DataLevelMap(Map<String,String> m) {
        super(m);
    }
}
