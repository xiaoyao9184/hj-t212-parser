package com.xy.format.hbt212.model.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xy.format.hbt212.model.*;

import java.util.List;
import java.util.Map;

/**
 * 混合
 * 序列化
 * @see com.xy.format.hbt212.model.Data 时使用
 * Created by xiaoyao9184 on 2017/12/19.
 */
public abstract class CpDataSerializationMixin {

    @JsonProperty("SystemTime")
    abstract String getSystemTime();

    @JsonProperty("QN")
    abstract String getQn();

    @JsonProperty("QnRtn")
    abstract String getQnRtn();

    @JsonProperty("ExeRtn")
    abstract String getExeRtn();

    @JsonProperty("RtdInterval")
    abstract int getRtdInterval();

    @JsonProperty("MinInterval")
    abstract int getMinInterval();

    @JsonProperty("RestartTime")
    abstract String getRestartTime();

    @JsonProperty("AlarmTime")
    abstract String getAlarmTime();

    @JsonProperty("AlarmType")
    abstract String getAlarmType();

    @JsonProperty("ReportTarget")
    abstract String getReportTarget();

    @JsonProperty("PolId")
    abstract String getPolId();

    @JsonProperty("BeginTime")
    abstract String getBeginTime();

    @JsonProperty("EndTime")
    abstract String getEndTime();

    @JsonProperty("DataTime")
    abstract String getDataTime();

    @JsonProperty("ReportTime")
    abstract String getReportTime();

    @JsonProperty("DayStdValue")
    abstract String getDayStdValue();

    @JsonProperty("NightStdValue")
    abstract String getNightStdValue();

    @JsonProperty("PNO")
    abstract int getpNo();

    @JsonProperty("PNUM")
    abstract int getpNum();

    @JsonProperty("PW")
    abstract String getPw();

    @JsonProperty("NewPW")
    abstract String getNewPW();

    @JsonProperty("OverTime")
    abstract int getOverTime();

    @JsonProperty("ReCount")
    abstract int getReCount();

    @JsonProperty("WarnTime")
    abstract int getWarnTime();

    @JsonProperty("Ctime")
    abstract int getcTime();

    @JsonProperty("VaseNo")
    abstract int getVaseNo();

    @JsonProperty("CstartTime")
    abstract String getcStartTime();

    @JsonProperty("Stime")
    abstract int getsTime();

    @JsonProperty("InfoId")
    abstract String getInfoId();

    @JsonProperty("Flag")
    abstract List<DataFlag> getDataFlag();

    @JsonProperty("Pollution")
    abstract Map<String, Pollution> getPollution();

    @JsonProperty("Device")
    abstract Map<String,Device> getDevice();

    @JsonProperty("Info")
    abstract Map<String,LiveSide> getLiveSide();

}
