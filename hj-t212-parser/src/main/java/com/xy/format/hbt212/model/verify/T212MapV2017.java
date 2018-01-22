package com.xy.format.hbt212.model.verify;

import com.xy.format.hbt212.core.validator.clazz.FieldC;
import com.xy.format.hbt212.core.validator.clazz.FieldN;
import com.xy.format.hbt212.core.validator.clazz.FieldValidDate;
import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.model.verify.groups.T212MapLevelGroup;

import java.util.Map;

/**
 * T212 Map
 * 解决无法对MAP进行验证定义问题
 * Created by xiaoyao9184 on 2018/1/10.
 */
//@FieldMissing(groups = T212Map.Group.DataLevel.class)
//@ValueRange(groups = T212Map.Group.DataLevel.class)
@FieldValidDate(field = "QN",
        value = @ValidDate(format = "yyyyMMddHHmmssSSS"))
//@FieldValidDate(field = "QN", groups = ModeGroup.Strict.class,
//        value = @ValidDate(field = "QN", format = "yyyyMMddHHmmssSSS", optional = false))
@FieldC(field = "ST",
        value = @C(len = 2))
//@NotBlank(groups = ModeGroup.Strict.class)
@FieldC(field = "CN",
        value = @C(len = 4))
//@NotBlank(groups = ModeGroup.Strict.class)
@FieldC(field = "PW",
        value = @C(len = 6))
//@NotBlank(groups = ModeGroup.Strict.class)
@FieldC(field = "MN",
        value = @C(len = 24))
//@NotBlank(groups = ModeGroup.Strict.class)
@FieldN(field = "Flag",
        value = @N(integer = 3))
@FieldN(field = "PNUM",
        value = @N(integer = 4))
//@Min(value = 1, groups = ModeGroup.UseSubPacket.class)
@FieldN(field = "PNO",
        value = @N(integer = 4))
//@Min(value = 1, groups = ModeGroup.UseSubPacket.class)
@FieldC(field = "CP", groups = { T212MapLevelGroup.DataLevel.class },
        value = @C(len = 960))

@Deprecated
public class T212MapV2017<K,V>
        extends T212Map<K,V> {

    public T212MapV2017(Map<K, V> m) {
        super(m);
    }
}
