package com.xy.format.hbt212.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.model.verify.groups.ModeGroup;
import com.xy.format.hbt212.model.verify.groups.VersionGroup;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

/**
 * 数据区
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class CpData {

    public static String LIVESIDE = "LiveSide";
    public static String DEVICE = "Device";
    public static String POLLUTION = "Pollution";

    @ValidDate(format = "yyyyMMddHHmmss")
    @JsonProperty("SystemTime")
    @JsonbProperty("SystemTime")
    private String systemTime;

    @ValidDate(format = "yyyyMMddHHmmssSSS", groups = VersionGroup.V2005.class)
    @JsonProperty("QN")
    @JsonbProperty("QN")
    private String qn;

    @N(integer = 3)
    @JsonProperty("QnRtn")
    @JsonbProperty("QnRtn")
    private String qnRtn;

    @N(integer = 3)
    @JsonProperty("ExeRtn")
    @JsonbProperty("ExeRtn")
    private String exeRtn;

    @Max(value = 9999, groups = VersionGroup.V2005.class)
    @Min(value = 30, groups = VersionGroup.V2017.class)
    @Max(value = 3600, groups = VersionGroup.V2017.class)
    @JsonProperty("RtdInterval")
    @JsonbProperty("RtdInterval")
    private Integer rtdInterval;

    @Max(value = 99, groups = VersionGroup.V2017.class)
    @JsonProperty("MinInterval")
    @JsonbProperty("MinInterval")
    private Integer minInterval;

    @ValidDate(format = "yyyyMMddHHmmss", groups = VersionGroup.V2017.class)
    @JsonProperty("RestartTime")
    @JsonbProperty("RestartTime")
    private String restartTime;

    @ValidDate(format = "yyyyMMddHHmmss", groups = VersionGroup.V2005.class)
    @JsonbProperty("AlarmTime")
    @JsonProperty("AlarmTime")
    private String alarmTime;

    @N(integer = 1, min = 0, max = 1, groups = VersionGroup.V2005.class)
    @JsonbProperty("AlarmType")
    @JsonProperty("AlarmType")
    private String alarmType;

    @N(integer = 20, groups = VersionGroup.V2005.class)
    @JsonbProperty("ReportTarget")
    @JsonProperty("ReportTarget")
    private String reportTarget;

    @C(len = 6)
    @JsonbProperty("PolId")
    @JsonProperty("PolId")
    private String polId;

    @ValidDate(format = "yyyyMMddHHmmss")
    @JsonProperty("BeginTime")
    @JsonbProperty("BeginTime")
    private String beginTime;

    @ValidDate(format = "yyyyMMddHHmmss")
    @JsonProperty("EndTime")
    @JsonbProperty("EndTime")
    private String endTime;

    @ValidDate(format = "yyyyMMddHHmmss")
    @JsonProperty("DataTime")
    @JsonbProperty("DataTime")
    private String dataTime;

    @N(integer = 4, groups = VersionGroup.V2005.class)
    @JsonProperty("ReportTime")
    @JsonbProperty("ReportTime")
    private String reportTime;

    @N(integer = 14, groups = VersionGroup.V2005.class)
    @JsonProperty("DayStdValue")
    @JsonbProperty("DayStdValue")
    private String dayStdValue;

    @N(integer = 14, groups = VersionGroup.V2005.class)
    @JsonProperty("NightStdValue")
    @JsonbProperty("NightStdValue")
    private String nightStdValue;

    @Max(value = 9999, groups = VersionGroup.V2005.class)
    @JsonProperty("PNO")
    @JsonbProperty("PNO")
    private Integer pNo;

    @Max(value = 9999, groups = VersionGroup.V2005.class)
    @JsonProperty("PNUM")
    @JsonbProperty("PNUM")
    private Integer pNum;

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
    private Integer overTime;

    @Max(value = 99)
    @JsonProperty("ReCount")
    @JsonbProperty("ReCount")
    private Integer reCount;

    @Max(value = 99999, groups = VersionGroup.V2005.class)
    @JsonProperty("WarnTime")
    @JsonbProperty("WarnTime")
    private Integer warnTime;

    @Max(value = 99, groups = VersionGroup.V2005.class)
    @Min(value = 0, groups = VersionGroup.V2017.class)
    @Max(value = 24, groups = VersionGroup.V2017.class)
    @JsonProperty("CTime")
    @JsonbProperty("CTime")
    @JsonAlias({ "Ctime", "cTime" })
    private Integer cTime;

    @Min(value = 0, groups = VersionGroup.V2017.class)
    @Max(value = 99, groups = VersionGroup.V2017.class)
    @JsonProperty("VaseNo")
    @JsonbProperty("VaseNo")
    private Integer vaseNo;

    @ValidDate(format = "HHmmss", groups = VersionGroup.V2017.class)
    @JsonProperty("CstartTime")
    @JsonbProperty("CstartTime")
    private String cStartTime;

    @Min(value = 0, groups = VersionGroup.V2017.class)
    @Max(value = 120, groups = VersionGroup.V2017.class)
    @JsonProperty("Stime")
    @JsonbProperty("Stime")
    private Integer sTime;

    @C(len = 6, groups = VersionGroup.V2017.class)
    @JsonProperty("InfoId")
    @JsonbProperty("InfoId")
    private String infoId;

    @JsonProperty("Flag")
    @JsonbProperty("Flag")
    private List<DataFlag> dataFlag;

    @JsonProperty("Pollution")
    @JsonbProperty("Pollution")
    private Map<String,Pollution> pollution;

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

    public Integer getRtdInterval() {
        return rtdInterval;
    }

    public void setRtdInterval(Integer rtdInterval) {
        this.rtdInterval = rtdInterval;
    }

    public Integer getMinInterval() {
        return minInterval;
    }

    public void setMinInterval(Integer minInterval) {
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

    public Integer getpNo() {
        return pNo;
    }

    public void setpNo(Integer pNo) {
        this.pNo = pNo;
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
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

    public Integer getOverTime() {
        return overTime;
    }

    public void setOverTime(Integer overTime) {
        this.overTime = overTime;
    }

    public Integer getReCount() {
        return reCount;
    }

    public void setReCount(Integer reCount) {
        this.reCount = reCount;
    }

    public Integer getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Integer warnTime) {
        this.warnTime = warnTime;
    }

    public Integer getcTime() {
        return cTime;
    }

    public void setcTime(Integer cTime) {
        this.cTime = cTime;
    }

    public Integer getVaseNo() {
        return vaseNo;
    }

    public void setVaseNo(Integer vaseNo) {
        this.vaseNo = vaseNo;
    }

    public String getcStartTime() {
        return cStartTime;
    }

    public void setcStartTime(String cStartTime) {
        this.cStartTime = cStartTime;
    }

    public Integer getsTime() {
        return sTime;
    }

    public void setsTime(Integer sTime) {
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
