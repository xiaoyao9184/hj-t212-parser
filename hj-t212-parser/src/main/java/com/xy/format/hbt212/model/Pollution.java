package com.xy.format.hbt212.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.model.verify.groups.VersionGroup;
import io.swagger.annotations.ApiModelProperty;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 污染因子
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class Pollution {

    @ApiModelProperty(value = "污染物采样时间", name = "SampleTime")
    @ValidDate(format = "yyyyMMddHHmmssSSS", groups = VersionGroup.V2017.class)
    @JsonProperty("SampleTime")
    @JsonbProperty("SampleTime")
    private String sampleTime;

    @ApiModelProperty(value = "污染物实时采样数据", name = "Rtd")
    @JsonProperty("Rtd")
    @JsonbProperty("Rtd")
    private BigDecimal rtd;

    @ApiModelProperty(value = "污染物指定时间内最小值", name = "Min")
    @JsonProperty("Min")
    @JsonbProperty("Min")
    private BigDecimal min;

    @ApiModelProperty(value = "污染物指定时间内平均值", name = "Avg")
    @JsonProperty("Avg")
    @JsonbProperty("Avg")
    private BigDecimal avg;

    @ApiModelProperty(value = "污染物指定时间内最大值", name = "Max")
    @JsonProperty("Max")
    @JsonbProperty("Max")
    private BigDecimal max;

    @ApiModelProperty(value = "污染物实时采样折算数据", name = "ZsRtd")
    @JsonProperty("ZsRtd")
    @JsonbProperty("ZsRtd")
    private BigDecimal zsRtd;

    @ApiModelProperty(value = "污染物指定时间内最小折算值", name = "ZsMin")
    @JsonProperty("ZsMin")
    @JsonbProperty("ZsMin")
    private BigDecimal zsMin;

    @ApiModelProperty(value = "污染物指定时间内平均折算值", name = "ZsAvg")
    @JsonProperty("ZsAvg")
    @JsonbProperty("ZsAvg")
    private BigDecimal zsAvg;

    @ApiModelProperty(value = "污染物指定时间内最大折算值", name = "ZsMax")
    @JsonProperty("ZsMax")
    @JsonbProperty("ZsMax")
    private BigDecimal zsMax;

    @ApiModelProperty(value = "监测污染物实时数据标记", name = "Flag", allowableValues = "可扩充[P,F,C,M,T,D,S,N,0,1,2,3]")
    @C(len = 1, groups = VersionGroup.V2017.class)
    @JsonProperty("Flag")
    @JsonbProperty("Flag")
    private String flag;

    @ApiModelProperty(value = "监测仪器扩充数据标记", name = "EFlag")
    @C(len = 4, groups = VersionGroup.V2017.class)
    @JsonProperty("EFlag")
    @JsonbProperty("EFlag")
    private String eFlag;

    @ApiModelProperty(value = "污染物指定时间内累计值", name = "Cou")
    @JsonProperty("Cou")
    @JsonbProperty("Cou")
    private BigDecimal cou;


    @ApiModelProperty(value = "设备运行状态的实时采样值", name = "RS")
    @Max(value = 1, groups = VersionGroup.V2005.class)
//    @N(integer = 1, groups = VersionGroup.V2005.class)
    @JsonProperty("RS")
    @JsonbProperty("RS")
    private Integer rs;

    @ApiModelProperty(value = "设备指定时间内的运行时间", name = "RT")
    @DecimalMax(value = "24", groups = VersionGroup.V2005.class)
//    @N(integer = 14, fraction = 2, groups = VersionGroup.V2005.class)
    @JsonProperty("RT")
    @JsonbProperty("RT")
    private BigDecimal rt;

    @ApiModelProperty(value = "污染物报警期间内采样值", name = "Ala")
    @N(integer = 14, fraction = 2, groups = VersionGroup.V2005.class)
    @JsonProperty("Ala")
    @JsonbProperty("Ala")
    private BigDecimal ala;

    @ApiModelProperty(value = "污染物报警上限值", name = "UpValue")
    @N(integer = 14, fraction = 2, groups = VersionGroup.V2005.class)
    @JsonProperty("UpValue")
    @JsonbProperty("UpValue")
    private BigDecimal upValue;

    @ApiModelProperty(value = "污染物报警下限值", name = "LowValue")
    @N(integer = 14, fraction = 2, groups = VersionGroup.V2005.class)
    @JsonProperty("LowValue")
    @JsonbProperty("LowValue")
    private BigDecimal lowValue;

    @ApiModelProperty(value = "噪声监测日历史数据", name = "Data")
    @N(integer = 14, fraction = 2, groups = VersionGroup.V2005.class)
    @N(integer = 3, fraction = 1, groups = VersionGroup.V2017.class)
    @JsonProperty("Data")
    @JsonbProperty("Data")
    private String data;

    @ApiModelProperty(value = "噪声昼间历史数据", name = "DayData")
    @N(integer = 14, fraction = 2, groups = VersionGroup.V2005.class)
    @N(integer = 3, fraction = 1, groups = VersionGroup.V2017.class)
    @JsonProperty("DayData")
    @JsonbProperty("DayData")
    private String dayData;

    @ApiModelProperty(value = "噪声夜间历史数据", name = "NightData")
    @N(integer = 14, fraction = 2, groups = VersionGroup.V2005.class)
    @N(integer = 3, fraction = 1, groups = VersionGroup.V2017.class)
    @JsonProperty("NightData")
    @JsonbProperty("NightData")
    private String nightData;


    public String getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(String sampleTime) {
        this.sampleTime = sampleTime;
    }

    public BigDecimal getRtd() {
        return rtd;
    }

    public void setRtd(BigDecimal rtd) {
        this.rtd = rtd;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getZsRtd() {
        return zsRtd;
    }

    public void setZsRtd(BigDecimal zsRtd) {
        this.zsRtd = zsRtd;
    }

    public BigDecimal getZsMin() {
        return zsMin;
    }

    public void setZsMin(BigDecimal zsMin) {
        this.zsMin = zsMin;
    }

    public BigDecimal getZsAvg() {
        return zsAvg;
    }

    public void setZsAvg(BigDecimal zsAvg) {
        this.zsAvg = zsAvg;
    }

    public BigDecimal getZsMax() {
        return zsMax;
    }

    public void setZsMax(BigDecimal zsMax) {
        this.zsMax = zsMax;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String geteFlag() {
        return eFlag;
    }

    public void seteFlag(String eFlag) {
        this.eFlag = eFlag;
    }

    public BigDecimal getCou() {
        return cou;
    }

    public void setCou(BigDecimal cou) {
        this.cou = cou;
    }

    public Integer getRs() {
        return rs;
    }

    public void setRs(Integer rs) {
        this.rs = rs;
    }

    public BigDecimal getRt() {
        return rt;
    }

    public void setRt(BigDecimal rt) {
        this.rt = rt;
    }

    public BigDecimal getAla() {
        return ala;
    }

    public void setAla(BigDecimal ala) {
        this.ala = ala;
    }

    public BigDecimal getUpValue() {
        return upValue;
    }

    public void setUpValue(BigDecimal upValue) {
        this.upValue = upValue;
    }

    public BigDecimal getLowValue() {
        return lowValue;
    }

    public void setLowValue(BigDecimal lowValue) {
        this.lowValue = lowValue;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDayData() {
        return dayData;
    }

    public void setDayData(String dayData) {
        this.dayData = dayData;
    }

    public String getNightData() {
        return nightData;
    }

    public void setNightData(String nightData) {
        this.nightData = nightData;
    }
}
