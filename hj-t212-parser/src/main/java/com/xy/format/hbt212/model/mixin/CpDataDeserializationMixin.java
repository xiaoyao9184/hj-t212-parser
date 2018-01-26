package com.xy.format.hbt212.model.mixin;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xy.format.hbt212.model.*;

import java.util.List;
import java.util.Map;

/**
 * 混合
 * 序列化
 * @see Data 时使用
 * Created by xiaoyao9184 on 2017/12/19.
 */
@JsonIgnoreProperties(value={ "Flag","Pollution","Device","LiveSide" }, allowGetters=true)
public abstract class CpDataDeserializationMixin {

    @JsonProperty("SystemTime")
    abstract void setSystemTime(String systemTime);

    @JsonProperty("QN")
    abstract void setQn(String qn);

    @JsonProperty("QnRtn")
    abstract void setQnRtn(String qnRtn);

    @JsonProperty("ExeRtn")
    abstract void setExeRtn(String exeRtn);

    @JsonProperty("RtdInterval")
    abstract void setRtdInterval(int rtdInterval);

    @JsonProperty("MinInterval")
    abstract void setMinInterval(int minInterval);

    @JsonProperty("RestartTime")
    abstract void setRestartTime(String restartTime);

    @JsonProperty("AlarmTime")
    abstract void setAlarmTime(String alarmTime);

    @JsonProperty("AlarmType")
    abstract void setAlarmType(String alarmType);

    @JsonProperty("ReportTarget")
    abstract void setReportTarget(String reportTarget);

    @JsonProperty("PolId")
    abstract void setPolId(String polId);

    @JsonProperty("BeginTime")
    abstract void setBeginTime(String beginTime);

    @JsonProperty("EndTime")
    abstract void setEndTime(String endTime);

    @JsonProperty("DataTime")
    abstract void setDataTime(String dataTime);

    @JsonProperty("ReportTime")
    abstract void setReportTime(String reportTime);

    @JsonProperty("DayStdValue")
    abstract void setDayStdValue(String dayStdValue);

    @JsonProperty("NightStdValue")
    abstract void setNightStdValue(String nightStdValue);

    @JsonProperty("PNO")
    abstract void setpNo(int pNo);

    @JsonProperty("PNUM")
    abstract void setpNum(int pNum);

    @JsonProperty("PW")
    abstract void setPw(String pw);

    @JsonProperty("NewPW")
    abstract void setNewPW(String newPW);

    @JsonProperty("OverTime")
    abstract void setOverTime(int overTime);

    @JsonProperty("ReCount")
    abstract void setReCount(int reCount);

    @JsonProperty("WarnTime")
    abstract void setWarnTime(int warnTime);

//    @JsonProperty("Ctime")
    @JsonAlias({ "Ctime", "cTime" })
    abstract void setcTime(int cTime);

    @JsonProperty("VaseNo")
    abstract void setVaseNo(int vaseNo);

    @JsonProperty("CstartTime")
    abstract void setcStartTime(String cStartTime);

    @JsonProperty("Stime")
    abstract void setsTime(int sTime);

    @JsonProperty("InfoId")
    abstract void setInfoId(String infoId);

    @JsonIgnore
    abstract void setDataFlag(List<DataFlag> dataFlag);

    @JsonIgnore
    abstract void setPollution(Map<String, Pollution> pollution);

    @JsonIgnore
    abstract void setDevice(Map<String,Device> device);

    @JsonIgnore
    abstract void setLiveSide(Map<String,LiveSide> liveSide);

}
