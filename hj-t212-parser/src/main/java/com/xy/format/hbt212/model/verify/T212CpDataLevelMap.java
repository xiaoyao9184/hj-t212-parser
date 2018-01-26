package com.xy.format.hbt212.model.verify;

import com.xy.format.hbt212.core.validator.clazz.FieldC;
import com.xy.format.hbt212.core.validator.clazz.FieldN;
import com.xy.format.hbt212.core.validator.clazz.FieldValidDate;
import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.model.verify.groups.ModeGroup;
import com.xy.format.hbt212.model.verify.groups.VersionGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * T212 Map
 * 解决无法对MAP进行验证定义问题
 * Created by xiaoyao9184 on 2018/1/10.
 */
@FieldValidDate(field = "QN",
        value = @ValidDate(format = "yyyyMMddHHmmssSSS"))
@FieldC(field = "ST",
        value = @C(len = 2))
@FieldC(field = "CN",
        value = @C(len = 4))
@FieldC(field = "PW",
        value = @C(len = 6))
@FieldC(field = "MN",
        value = @C(len = 14))
@FieldN(field = "Flag",
        value = @N(integer = 3))
@FieldN(field = "PNUM", groups = ModeGroup.UseSubPacket.class,
        value = @N(integer = 4, optional = false))
@FieldN(field = "PNO", groups = ModeGroup.UseSubPacket.class,
        value = @N(integer = 4, optional = false))
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
    @FieldN(field = "RtdInterval", groups = VersionGroup.V2005.class,
            value = @N(integer = 4))
    @FieldN(field = "RtdInterval", groups = VersionGroup.V2017.class,
            value = @N(integer = 4, min = 30, max = 3600))

    @FieldValidDate(field = "QN", groups = VersionGroup.V2005.class,
            value = @ValidDate(format = "yyyyMMddHHmmssSSS"))

    @FieldN(field = "MinInterval", groups = VersionGroup.V2017.class,
            value = @N(integer = 2))
    @FieldValidDate(field = "RestartTime", groups = VersionGroup.V2017.class,
            value = @ValidDate(format = "yyyyMMddHHmmss"))


    //污染物
    @FieldC(field = ".*-Flag", regex = true,
            value = @C(len = 1))

    @FieldN(field = ".*-Rtd", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Min", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Avg", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Max", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-ZsRtd", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-ZsMin", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-ZsAvg", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-ZsMax", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Cou", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Ala", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-UpValue", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-LowValue", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))

    @FieldValidDate(field = ".*-SampleTime", regex = true, groups = VersionGroup.V2017.class,
            value = @ValidDate(format = "yyyyMMddHHmmss"))
    @FieldC(field = ".*-EFlag", regex = true, groups = VersionGroup.V2017.class,
            value = @C(len = 4))

    //污染治理设施
    @FieldN(field = ".*-RS", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 1, min = 0, max = 1))
    @FieldN(field = ".*-RT", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = "SB.*-RS", regex = true, groups = VersionGroup.V2017.class,
            value = @N(integer = 1))
    @FieldN(field = "SB.*-RT", regex = true, groups = VersionGroup.V2017.class,
            value = @N(integer = 2, fraction = 2, min = 0, max = 24))


    //噪声
    @FieldN(field = ".*-Data", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-DayData", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-NightData", regex = true, groups = VersionGroup.V2005.class,
            value = @N(integer = 14, fraction = 2))
    @FieldN(field = ".*-Data", regex = true, groups = VersionGroup.V2017.class,
            value = @N(integer = 3, fraction = 1))
    @FieldN(field = ".*-DayData", regex = true, groups = VersionGroup.V2017.class,
            value = @N(integer = 3, fraction = 1))
    @FieldN(field = ".*-NightData", regex = true, groups = VersionGroup.V2017.class,
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
    @FieldN(field = "OverTime", groups = VersionGroup.V2005.class,
            value = @N(integer = 5))
    @FieldN(field = "OverTime", groups = VersionGroup.V2017.class,
            value = @N(integer = 2))
    @FieldN(field = "ReCount",
            value = @N(integer = 2))
    @FieldN(field = "CTime", groups = VersionGroup.V2005.class,
            value = @N(integer = 2))
    @FieldN(field = "Ctime", groups = VersionGroup.V2017.class,
            value = @N(integer = 2, max = 24))

    @FieldValidDate(field = "AlarmTime", groups = VersionGroup.V2005.class,
            value = @ValidDate(format = "yyyyMMddHHmmss"))
    @FieldN(field = "AlarmType", groups = VersionGroup.V2005.class,
            value = @N(integer = 1, min = 0, max = 1))
    @FieldN(field = "ReportTarget", groups = VersionGroup.V2005.class,
            value = @N(integer = 20))
    @FieldN(field = "ReportTime", groups = VersionGroup.V2005.class,
            value = @N(integer = 4))
    @FieldN(field = "DayStdValue", groups = VersionGroup.V2005.class,
            value = @N(integer = 14))
    @FieldN(field = "NightStdValue", groups = VersionGroup.V2005.class,
            value = @N(integer = 14))
    @FieldN(field = "WarnTime", groups = VersionGroup.V2005.class,
            value = @N(integer = 5))
    @FieldN(field = "Flag", groups = VersionGroup.V2005.class,
            value = @N(integer = 3))
    @FieldN(field = "PNUM", groups = VersionGroup.V2005.class,
            value = @N(integer = 4))
    @FieldN(field = "PNO", groups = VersionGroup.V2005.class,
            value = @N(integer = 4))
    @FieldC(field = "PW", groups = VersionGroup.V2005.class,
            value = @C(len = 6))

    @FieldC(field = "NewPW", groups = VersionGroup.V2017.class,
            value = @C(len = 6))
    @FieldN(field = "VaseNo", groups = VersionGroup.V2017.class,
            value = @N(integer = 2))
    @FieldValidDate(field = "CstartTime", groups = VersionGroup.V2017.class,
            value = @ValidDate(format = "HHmmss"))
    @FieldN(field = "Stime", groups = VersionGroup.V2017.class,
            value = @N(integer = 4))
    @FieldC(field = "InfoId", groups = VersionGroup.V2017.class,
            value = @C(len = 6))
    @FieldC(field = ".*-SN", regex = true, groups = VersionGroup.V2017.class,
            value = @C(len = 24))
    public class Cp
            extends T212Map<String, String> {

        public Cp(Map<String, String> m) {
            super(m);
        }
    }

}

