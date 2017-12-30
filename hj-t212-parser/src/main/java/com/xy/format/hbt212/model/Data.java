package com.xy.format.hbt212.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class Data {

    @Pattern(regexp = "\\d{14}")
    @JsonProperty("SystemTime")
    @JsonbProperty("SystemTime")
    private String systemTime;

    @Pattern(regexp = "\\d{20}")
    @JsonProperty("QN")
    @JsonbProperty("QN")
    private String qn;

    @Pattern(regexp = "\\d{3}")
    @JsonProperty("QnRtn")
    @JsonbProperty("QnRtn")
    private String qnRtn;

    @Pattern(regexp = "\\d{3}")
    @JsonProperty("ExeRtn")
    @JsonbProperty("ExeRtn")
    private String exeRtn;

    @Pattern(regexp = "\\d{4}")
    @JsonProperty("RtdInterval")
    @JsonbProperty("RtdInterval")
    private String rtdInterval;

    @JsonProperty("Pollution")
    @JsonbProperty("Pollution")
    private Map<String,DataPollution> pollution;

    @Pattern(regexp = "\\d{14}")
    @JsonbProperty("AlarmTime")
    @JsonProperty("AlarmTime")
    private String alarmTime;

    @Pattern(regexp = "\\d")
    @JsonbProperty("AlarmType")
    @JsonProperty("AlarmType")
    private String alarmType;

    @Pattern(regexp = "\\d{20}")
    @JsonbProperty("ReportTarget")
    @JsonProperty("ReportTarget")
    private String reportTarget;

    @Pattern(regexp = "\\w{3}")
    @JsonbProperty("PolId")
    @JsonProperty("PolId")
    private String polId;

    @Pattern(regexp = "\\d{14}")
    @JsonProperty("BeginTime")
    @JsonbProperty("BeginTime")
    private String beginTime;

    @Pattern(regexp = "\\d{14}")
    @JsonProperty("EndTime")
    @JsonbProperty("EndTime")
    private String endTime;

    @Pattern(regexp = "\\d{14}")
    @JsonProperty("DataTime")
    @JsonbProperty("DataTime")
    private String dataTime;

    @Pattern(regexp = "\\d{4}")
    @JsonProperty("ReportTime")
    @JsonbProperty("ReportTime")
    private String reportTime;

    @Pattern(regexp = "\\d{14}")
    @JsonProperty("DayStdValue")
    @JsonbProperty("DayStdValue")
    private String dayStdValue;

    @Pattern(regexp = "\\d{14}")
    @JsonProperty("NightStdValue")
    @JsonbProperty("NightStdValue")
    private String nightStdValue;

    @JsonProperty("Flag")
    @JsonbProperty("Flag")
    private List<SegmentFlag> segmentFlag;

    @Pattern(regexp = "\\d{4}")
    @JsonProperty("PNO")
    @JsonbProperty("PNO")
    private String pNo;

    @Pattern(regexp = "\\d{4}")
    @JsonProperty("PNUM")
    @JsonbProperty("PNUM")
    private String pNum;

    @Pattern(regexp = "\\w{6}")
    @JsonProperty("PW")
    @JsonbProperty("PW")
    private String pw;

    @Pattern(regexp = "\\d{5}")
    @JsonProperty("OverTime")
    @JsonbProperty("OverTime")
    private String overTime;

    @Pattern(regexp = "\\d{2}")
    @JsonProperty("ReCount")
    @JsonbProperty("ReCount")
    private String reCount;

    @Pattern(regexp = "\\d{5}")
    @JsonProperty("WarnTime")
    @JsonbProperty("WarnTime")
    private String warnTime;

    @Pattern(regexp = "\\d{2}")
    @JsonProperty("CTime")
    @JsonbProperty("CTime")
    private String cTime;


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

    public String getRtdInterval() {
        return rtdInterval;
    }

    public void setRtdInterval(String rtdInterval) {
        this.rtdInterval = rtdInterval;
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

    public Map<String, DataPollution> getPollution() {
        return pollution;
    }

    public void setPollution(Map<String, DataPollution> pollution) {
        this.pollution = pollution;
    }

    public List<SegmentFlag> getSegmentFlag() {
        return segmentFlag;
    }

    public void setSegmentFlag(List<SegmentFlag> segmentFlag) {
        this.segmentFlag = segmentFlag;
    }

    public String getpNo() {
        return pNo;
    }

    public void setpNo(String pNo) {
        this.pNo = pNo;
    }

    public String getpNum() {
        return pNum;
    }

    public void setpNum(String pNum) {
        this.pNum = pNum;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public String getReCount() {
        return reCount;
    }

    public void setReCount(String reCount) {
        this.reCount = reCount;
    }

    public String getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(String warnTime) {
        this.warnTime = warnTime;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }
}
