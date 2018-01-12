package com.xy.format.hbt212.model.verify;

import com.xy.format.hbt212.model.verify.groups.TypeGroup;

/**
 * 数据区 元素
 * Created by xiaoyao9184 on 2017/12/19.
 */
public enum CpDataElement {
    SystemTime(TypeGroup.YYYYMMDDhhmmss.class),
    QnRtn(TypeGroup.N3.class),
    ExeRtn(TypeGroup.N3.class),
    RtdInterval(TypeGroup.N4.class),
    MinInterval(TypeGroup.N2.class),
    RestartTime(TypeGroup.YYYYMMDDhhmmss.class),
    xxxxxx_SampleTime(TypeGroup.YYYYMMDDhhmmss.class),
    xxxxxx_Rtd,
    xxxxxx_Min,
    xxxxxx_Avg,
    xxxxxx_Max,
    xxxxxx_ZsRtd,
    xxxxxx_ZsMin,
    xxxxxx_ZsAvg,
    xxxxxx_ZsMax,
    xxxxxx_Flag(TypeGroup.C1.class),
    xxxxxx_EFlag(TypeGroup.C4.class),
    xxxxxx_Cou,
    SBxxx_RS(TypeGroup.N1.class),
    SBxxx_RT(TypeGroup.N2_2.class),
    xxxxxx_Data(TypeGroup.N3_1.class),
    xxxxxx_DayData(TypeGroup.N3_1.class),
    xxxxxx_NightData(TypeGroup.N3_1.class),

    PolId(TypeGroup.C6.class),
    BeginTime(TypeGroup.YYYYMMDDhhmmss.class),
    EndTime(TypeGroup.YYYYMMDDhhmmss.class),
    DataTime(TypeGroup.YYYYMMDDhhmmss.class),
    NewPW(TypeGroup.C6.class),
    OverTime(TypeGroup.N2.class),
    ReCount(TypeGroup.N2.class),
    VaseNo(TypeGroup.N2.class),
    CstartTime(TypeGroup.hhmmss.class),
    Ctime(Group.Ctime.class),
    Stime(Group.Stime.class),
    xxxxxx_Info,
    InfoId(TypeGroup.C6.class),
    xxxxxx_SN(TypeGroup.C24.class);

    private Class group;

    CpDataElement(){
    }
    CpDataElement(Class group){
        this.group = group;
    }

    public Class getGroup() {
        return group;
    }

    public interface Group {
        interface SystemTime{}
        interface QnRtn{}
        interface ExeRtn{}
        interface RtdInterval{}
        interface MinInterval{}
        interface RestartTime{}
        interface xxxxxx_SampleTime{}
        interface xxxxxx_Rtd{}
        interface xxxxxx_Min{}
        interface xxxxxx_Avg{}
        interface xxxxxx_Max{}
        interface xxxxxx_ZsRtd{}
        interface xxxxxx_ZsMin{}
        interface xxxxxx_ZsAvg{}
        interface xxxxxx_ZsMax{}
        interface xxxxxx_Flag{}
        interface xxxxxx_EFlag{}
        interface xxxxxx_Cou{}
        interface SBxxx_RS{}
        interface SBxxx_RT{}
        interface xxxxxx_Data{}
        interface xxxxxx_DayData{}
        interface xxxxxx_NightData{}
        interface PolId{}
        interface BeginTime{}
        interface EndTime{}
        interface DataTime{}
        interface NewPW{}
        interface OverTime{}
        interface ReCount{}
        interface VaseNo{}
        interface CstartTime{}
        interface Ctime{}
        interface Stime{}
        interface xxxxxx_Info{}
        interface InfoId{}
        interface xxxxxx_SN{}
    }

}
