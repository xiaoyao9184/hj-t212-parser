package com.xy.format.hbt212.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.model.verify.groups.ModeGroup;
import com.xy.format.hbt212.model.verify.groups.VersionGroup;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * 数据区
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class CpData {

    @ValidDate(format = "yyyyMMddHHmmssSSS")
    @ValidDate(format = "yyyyMMddHHmmssSSS", optional = false, groups = ModeGroup.Strict.class)
//    @Pattern(regexp = "\\d{14}")
    @JsonProperty("SystemTime")
    @JsonbProperty("SystemTime")
    private String systemTime;

    @ValidDate(format = "yyyyMMddHHmmssSSS", groups = VersionGroup.V2005.class)
    @JsonProperty("QN")
    @JsonbProperty("QN")
    private String qn;

    @N(integer = 3)
//    @Pattern(regexp = "\\d{3}")
    @JsonProperty("QnRtn")
    @JsonbProperty("QnRtn")
    private String qnRtn;

    @N(integer = 3)
//    @Pattern(regexp = "\\d{3}")
    @JsonProperty("ExeRtn")
    @JsonbProperty("ExeRtn")
    private String exeRtn;

    @Min(value = 30, groups = VersionGroup.V2017.class)
    @Max(value = 3600, groups = VersionGroup.V2017.class)
//    @N(integer = 4, groups = VersionGroup.V2017.class)
//    @Pattern(regexp = "\\d{4}")
    @JsonProperty("RtdInterval")
    @JsonbProperty("RtdInterval")
    private int rtdInterval;

    @Max(value = 99, groups = VersionGroup.V2017.class)
//    @Min(value = 30, groups = VersionGroup.V2017.class)
//    @Max(value = 3600, groups = VersionGroup.V2017.class)
//    @N(integer = 2, groups = VersionGroup.V2017.class)
//    @Pattern(regexp = "\\d{4}")
    @JsonProperty("MinInterval")
    @JsonbProperty("MinInterval")
    private int minInterval;

//    @Pattern(regexp = "\\d{14}")
    @ValidDate(format = "yyyyMMddHHmmssSSS", groups = VersionGroup.V2017.class)
    @JsonProperty("RestartTime")
    @JsonbProperty("RestartTime")
    private String restartTime;

    @JsonProperty("Pollution")
    @JsonbProperty("Pollution")
    private Map<String,Pollution> pollution;

    @ValidDate(format = "yyyyMMddHHmmss", groups = VersionGroup.V2005.class)
    @JsonbProperty("AlarmTime")
    @JsonProperty("AlarmTime")
    private String alarmTime;

//    @N(integer = 1, groups = VersionGroup.V2005.class)
    @Pattern(regexp = "[01]", groups = VersionGroup.V2005.class)
    @JsonbProperty("AlarmType")
    @JsonProperty("AlarmType")
    private String alarmType;

    @N(integer = 20, groups = VersionGroup.V2005.class)
//    @Pattern(regexp = "\\d{20}", groups = VersionGroup.V2005.class)
    @JsonbProperty("ReportTarget")
    @JsonProperty("ReportTarget")
    private String reportTarget;

    @C(len = 6)
//    @Pattern(regexp = "\\w{3}")
    @JsonbProperty("PolId")
    @JsonProperty("PolId")
    private String polId;

    @ValidDate(format = "yyyyMMddHHmmssSSS")
//    @Pattern(regexp = "\\d{14}")
    @JsonProperty("BeginTime")
    @JsonbProperty("BeginTime")
    private String beginTime;

    @ValidDate(format = "yyyyMMddHHmmssSSS")
//    @Pattern(regexp = "\\d{14}")
    @JsonProperty("EndTime")
    @JsonbProperty("EndTime")
    private String endTime;

    @ValidDate(format = "yyyyMMddHHmmssSSS")
//    @Pattern(regexp = "\\d{14}")
    @JsonProperty("DataTime")
    @JsonbProperty("DataTime")
    private String dataTime;

    @N(integer = 4, groups = VersionGroup.V2005.class)
//    @Pattern(regexp = "\\d{4}")
    @JsonProperty("ReportTime")
    @JsonbProperty("ReportTime")
    private String reportTime;

    @N(integer = 14, groups = VersionGroup.V2005.class)
//    @Pattern(regexp = "\\d{14}")
    @JsonProperty("DayStdValue")
    @JsonbProperty("DayStdValue")
    private String dayStdValue;

    @N(integer = 14, groups = VersionGroup.V2005.class)
//    @Pattern(regexp = "\\d{14}")
    @JsonProperty("NightStdValue")
    @JsonbProperty("NightStdValue")
    private String nightStdValue;


    @JsonProperty("Flag")
    @JsonbProperty("Flag")
    private List<DataFlag> dataFlag;

    @Max(value = 9999, groups = VersionGroup.V2005.class)
    @JsonProperty("PNO")
    @JsonbProperty("PNO")
    private int pNo;

    @Max(value = 9999, groups = VersionGroup.V2005.class)
    @JsonProperty("PNUM")
    @JsonbProperty("PNUM")
    private int pNum;

    @C(len = 6, groups = VersionGroup.V2005.class)
    @JsonProperty("PW")
    @JsonbProperty("PW")
    private String pw;

    @C(len = 6, groups = VersionGroup.V2017.class)
    @JsonProperty("NewPW")
    @JsonbProperty("NewPW")
    private String newPW;

    @Max(value = 99999, groups = VersionGroup.V2005.class)
    @Max(value = 99, groups = VersionGroup.V2017.class)
    @JsonProperty("OverTime")
    @JsonbProperty("OverTime")
    private int overTime;

    @Max(value = 99)
    @JsonProperty("ReCount")
    @JsonbProperty("ReCount")
    private int reCount;

    @Max(value = 99999, groups = VersionGroup.V2005.class)
    @JsonProperty("WarnTime")
    @JsonbProperty("WarnTime")
    private int warnTime;

    @Max(value = 99, groups = VersionGroup.V2005.class)
    @Max(value = 24, groups = VersionGroup.V2017.class)
    @JsonProperty("CTime")
    @JsonbProperty("CTime")
    private int cTime;

    @Max(value = 99, groups = VersionGroup.V2017.class)
    @JsonProperty("VaseNo")
    @JsonbProperty("VaseNo")
    private int vaseNo;

    @ValidDate(format = "HHmmss", groups = VersionGroup.V2017.class)
    @JsonProperty("CstartTime")
    @JsonbProperty("CstartTime")
    private String cStartTime;

    @Max(value = 120, groups = VersionGroup.V2017.class)
    @JsonProperty("Stime")
    @JsonbProperty("Stime")
    private int sTime;

    @C(len = 6, groups = VersionGroup.V2017.class)
    @JsonProperty("InfoId")
    @JsonbProperty("InfoId")
    private String infoId;


    @JsonProperty("Device")
    @JsonbProperty("Device")
    private Map<String,Device> device;

    @JsonProperty("LiveSide")
    @JsonbProperty("LiveSide")
    private Map<String,LiveSide> liveSide;


    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public String getQn() {
        return qn;
    }

    public void setQn(String qn) {
        this.qn = qn;
    }

    public String getQnRtn() {
        return qnRtn;
    }

    public void setQnRtn(String qnRtn) {
        this.qnRtn = qnRtn;
    }

    public String getExeRtn() {
        return exeRtn;
    }

    public void setExeRtn(String exeRtn) {
        this.exeRtn = exeRtn;
    }

    public int getRtdInterval() {
        return rtdInterval;
    }

    public void setRtdInterval(int rtdInterval) {
        this.rtdInterval = rtdInterval;
    }

    public int getMinInterval() {
        return minInterval;
    }

    public void setMinInterval(int minInterval) {
        this.minInterval = minInterval;
    }

    public String getRestartTime() {
        return restartTime;
    }

    public void setRestartTime(String restartTime) {
        this.restartTime = restartTime;
    }

    public Map<String, Pollution> getPollution() {
        return pollution;
    }

    public void setPollution(Map<String, Pollution> pollution) {
        this.pollution = pollution;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getReportTarget() {
        return reportTarget;
    }

    public void setReportTarget(String reportTarget) {
        this.reportTarget = reportTarget;
    }

    public String getPolId() {
        return polId;
    }

    public void setPolId(String polId) {
        this.polId = polId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getDayStdValue() {
        return dayStdValue;
    }

    public void setDayStdValue(String dayStdValue) {
        this.dayStdValue = dayStdValue;
    }

    public String getNightStdValue() {
        return nightStdValue;
    }

    public void setNightStdValue(String nightStdValue) {
        this.nightStdValue = nightStdValue;
    }

    public List<DataFlag> getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(List<DataFlag> dataFlag) {
        this.dataFlag = dataFlag;
    }

    public int getpNo() {
        return pNo;
    }

    public void setpNo(int pNo) {
        this.pNo = pNo;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNewPW() {
        return newPW;
    }

    public void setNewPW(String newPW) {
        this.newPW = newPW;
    }

    public int getOverTime() {
        return overTime;
    }

    public void setOverTime(int overTime) {
        this.overTime = overTime;
    }

    public int getReCount() {
        return reCount;
    }

    public void setReCount(int reCount) {
        this.reCount = reCount;
    }

    public int getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(int warnTime) {
        this.warnTime = warnTime;
    }

    public int getcTime() {
        return cTime;
    }

    public void setcTime(int cTime) {
        this.cTime = cTime;
    }

    public int getVaseNo() {
        return vaseNo;
    }

    public void setVaseNo(int vaseNo) {
        this.vaseNo = vaseNo;
    }

    public String getcStartTime() {
        return cStartTime;
    }

    public void setcStartTime(String cStartTime) {
        this.cStartTime = cStartTime;
    }

    public int getsTime() {
        return sTime;
    }

    public void setsTime(int sTime) {
        this.sTime = sTime;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public Map<String,Device> getDevice() {
        return device;
    }

    public void setDevice(Map<String,Device> device) {
        this.device = device;
    }

    public Map<String,LiveSide> getLiveSide() {
        return liveSide;
    }

    public void setLiveSide(Map<String,LiveSide> liveSide) {
        this.liveSide = liveSide;
    }
}
