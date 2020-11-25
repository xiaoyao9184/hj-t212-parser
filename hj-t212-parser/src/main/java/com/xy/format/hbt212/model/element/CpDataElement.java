package com.xy.format.hbt212.model.element;

import com.xy.format.hbt212.model.verify.groups.GroupCommon;

/**
 * 数据区 元素
 * Created by xiaoyao9184 on 2017/12/19.
 */
public enum CpDataElement {
    SystemTime(GroupCommon.YYYYMMDDhhmmss.class),
    QnRtn(GroupCommon.N3.class),
    ExeRtn(GroupCommon.N3.class),
    RtdInterval(GroupCommon.N4.class),
    MinInterval(GroupCommon.N2.class),
    RestartTime(GroupCommon.YYYYMMDDhhmmss.class),
    xxxxxx_SampleTime(GroupCommon.YYYYMMDDhhmmss.class),
    xxxxxx_Rtd,
    xxxxxx_Min,
    xxxxxx_Avg,
    xxxxxx_Max,
    xxxxxx_ZsRtd,
    xxxxxx_ZsMin,
    xxxxxx_ZsAvg,
    xxxxxx_ZsMax,
    xxxxxx_Flag(GroupCommon.C1.class),
    xxxxxx_EFlag(GroupCommon.C4.class),
    xxxxxx_Cou,
    SBxxx_RS(GroupCommon.N1.class),
    SBxxx_RT(GroupCommon.N2_2.class),
    xxxxxx_Data(GroupCommon.N3_1.class),
    xxxxxx_DayData(GroupCommon.N3_1.class),
    xxxxxx_NightData(GroupCommon.N3_1.class),

    PolId(GroupCommon.C6.class),
    BeginTime(GroupCommon.YYYYMMDDhhmmss.class),
    EndTime(GroupCommon.YYYYMMDDhhmmss.class),
    DataTime(GroupCommon.YYYYMMDDhhmmss.class),
    NewPW(GroupCommon.C6.class),
    OverTime(GroupCommon.N2.class),
    ReCount(GroupCommon.N2.class),
    VaseNo(GroupCommon.N2.class),
    CstartTime(GroupCommon.hhmmss.class),
    Ctime(Group.Ctime.class),
    Stime(Group.Stime.class),
    xxxxxx_Info,
    InfoId(GroupCommon.C6.class),
    xxxxxx_SN(GroupCommon.C24.class);

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
