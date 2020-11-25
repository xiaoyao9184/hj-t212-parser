package com.xy.format.hbt212.model.expand;

import com.xy.format.hbt212.core.validator.clazz.FieldC;
import com.xy.format.hbt212.core.validator.clazz.FieldN;
import com.xy.format.hbt212.core.validator.clazz.FieldValidDate;
import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.model.verify.groups.Group;
import com.xy.format.hbt212.model.verify.groups.Intersect;

import java.util.HashMap;
import java.util.Map;

/**
 * T212 Map
 * 解决无法对MAP进行验证定义问题
 * Created by xiaoyao9184 on 2018/1/10.
 */
@FieldValidDate(field = "QN",
        value = @ValidDate(format = "yyyyMMddHHmmssSSS"))

@FieldC(field = "ST", groups = Group.Version.V2005.class,
        value = @C(len = 5))
@FieldC(field = "ST", groups = Group.Version.V2017.class,
        value = @C(len = 5))

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

@FieldN(field = "Flag", groups = Group.Version.V2005.class,
        value = @N(integer = 3))
@FieldN(field = "Flag", groups = Group.Version.V2017.class,
        value = @N(integer = 4))

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
public class T212CpDataLevelMap
        extends T212Map<String,Object> {

    public T212CpDataLevelMap(Map<String, Object> m) {
        super(m);
    }

    @SuppressWarnings("unchecked")
    public Cp getCp(){
        Map<String,String> map = (Map<String, String>) this.get("CP");
        if(map == null){
            map = new HashMap<>();
        }
        return new Cp(map);
    }

    //
    @FieldValidDate(field = "SystemTime",
            value = @ValidDate(format = "yyyyMMddHHmmss"))
    @FieldN(field = "QnRtn",
            value = @N(integer = 3))
    @FieldN(field = "ExeRtn",
            value = @N(integer = 3))
    @FieldN(field = "RtdInterval", groups = Group.Version.V2005.class,
            value = @N(integer = 4))
    @FieldN(field = "RtdInterval", groups = Group.Version.V2017.class,
            value = @N(integer = 4, min = 30, max = 3600))

    @FieldValidDate(field = "QN", groups = Group.Version.V2005.class,
            value = @ValidDate(format = "yyyyMMddHHmmssSSS"))

    @FieldN(field = "MinInterval", groups = Group.Version.V2017.class,
            value = @N(integer = 2))
    @FieldValidDate(field = "RestartTime", groups = Group.Version.V2017.class,
            value = @ValidDate(format = "yyyyMMddHHmmss"))


    //污染物
    @FieldC(field = ".*-Flag", regex = true,
            value = @C(len = 1))

    @FieldN(field = ".*-Rtd", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Min", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Avg", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Max", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-ZsRtd", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-ZsMin", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-ZsAvg", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-ZsMax", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Cou", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Ala", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-UpValue", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-LowValue", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))

    @FieldValidDate(field = ".*-SampleTime", regex = true, groups = Group.Version.V2017.class,
            value = @ValidDate(format = "yyyyMMddHHmmss"))
    @FieldC(field = ".*-EFlag", regex = true, groups = Group.Version.V2017.class,
            value = @C(len = 4))

    //污染治理设施
    @FieldN(field = ".*-RS", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 1, min = 0, max = 1))
    @FieldN(field = ".*-RT", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = "SB.*-RS", regex = true, groups = Group.Version.V2017.class,
            value = @N(integer = 1))
    @FieldN(field = "SB.*-RT", regex = true, groups = Group.Version.V2017.class,
            value = @N(integer = 2, fraction = 2, min = 0, max = 24))


    //噪声
    @FieldN(field = ".*-Data", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-DayData", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-NightData", regex = true, groups = Group.Version.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Data", regex = true, groups = Group.Version.V2017.class,
            value = @N(integer = 3, fraction = 1))
    @FieldN(field = ".*-DayData", regex = true, groups = Group.Version.V2017.class,
            value = @N(integer = 3, fraction = 1))
    @FieldN(field = ".*-NightData", regex = true, groups = Group.Version.V2017.class,
            value = @N(integer = 3, fraction = 1))


    //
    @FieldC(field = "PolId",
            value = @C(len = 6))
    @FieldValidDate(field = "BeginTime",
            value = @ValidDate(format = "yyyyMMddHHmmss"))
    @FieldValidDate(field = "EndTime",
            value = @ValidDate(format = "yyyyMMddHHmmss"))
    @FieldValidDate(field = "DataTime",
            value = @ValidDate(format = "yyyyMMddHHmmss"))
    @FieldN(field = "OverTime", groups = Group.Version.V2005.class,
            value = @N(integer = 5))
    @FieldN(field = "OverTime", groups = Group.Version.V2017.class,
            value = @N(integer = 2))
    @FieldN(field = "ReCount",
            value = @N(integer = 2))
    @FieldN(field = "CTime", groups = Group.Version.V2005.class,
            value = @N(integer = 2))
    @FieldN(field = "Ctime", groups = Group.Version.V2017.class,
            value = @N(integer = 2, max = 24))

    @FieldValidDate(field = "AlarmTime", groups = Group.Version.V2005.class,
            value = @ValidDate(format = "yyyyMMddHHmmss"))
    @FieldN(field = "AlarmType", groups = Group.Version.V2005.class,
            value = @N(integer = 1, min = 0, max = 1))
    @FieldN(field = "ReportTarget", groups = Group.Version.V2005.class,
            value = @N(integer = 20))
    @FieldN(field = "ReportTime", groups = Group.Version.V2005.class,
            value = @N(integer = 4))
    @FieldN(field = "DayStdValue", groups = Group.Version.V2005.class,
            value = @N(integer = 14))
    @FieldN(field = "NightStdValue", groups = Group.Version.V2005.class,
            value = @N(integer = 14))
    @FieldN(field = "WarnTime", groups = Group.Version.V2005.class,
            value = @N(integer = 5))
    @FieldN(field = "Flag", groups = Group.Version.V2005.class,
            value = @N(integer = 3))
    @FieldN(field = "PNUM", groups = Group.Version.V2005.class,
            value = @N(integer = 4))
    @FieldN(field = "PNO", groups = Group.Version.V2005.class,
            value = @N(integer = 4))
    @FieldC(field = "PW", groups = Group.Version.V2005.class,
            value = @C(len = 6))

    @FieldC(field = "NewPW", groups = Group.Version.V2017.class,
            value = @C(len = 6))
    @FieldN(field = "VaseNo", groups = Group.Version.V2017.class,
            value = @N(integer = 2))
    @FieldValidDate(field = "CstartTime", groups = Group.Version.V2017.class,
            value = @ValidDate(format = "HHmmss"))
    @FieldN(field = "Stime", groups = Group.Version.V2017.class,
            value = @N(integer = 4))
    @FieldC(field = "InfoId", groups = Group.Version.V2017.class,
            value = @C(len = 6))
    @FieldC(field = ".*-SN", regex = true, groups = Group.Version.V2017.class,
            value = @C(len = 24))
    public class Cp
            extends T212Map<String, String> {

        public Cp(Map<String, String> m) {
            super(m);
        }
    }

}

