package com.xy.format.hbt212.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class DataPollution {

    @ApiModelProperty(value = "污染物实时采样数据", name = "Rtd")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("Rtd")
    @JsonbProperty("Rtd")
    private BigDecimal rtd;

    @ApiModelProperty(value = "污染物指定时间内最小值", name = "Min")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("Min")
    @JsonbProperty("Min")
    private BigDecimal min;

    @ApiModelProperty(value = "污染物指定时间内平均值", name = "Avg")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("Avg")
    @JsonbProperty("Avg")
    private BigDecimal avg;

    @ApiModelProperty(value = "污染物指定时间内最大值", name = "Max")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("Max")
    @JsonbProperty("Max")
    private BigDecimal max;

    @ApiModelProperty(value = "污染物实时采样折算数据", name = "ZsRtd")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("ZsRtd")
    @JsonbProperty("ZsRtd")
    private BigDecimal zsRtd;

    @ApiModelProperty(value = "污染物指定时间内最小折算值", name = "ZsMin")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("ZsMin")
    @JsonbProperty("ZsMin")
    private BigDecimal zsMin;

    @ApiModelProperty(value = "污染物指定时间内平均折算值", name = "ZsAvg")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("ZsAvg")
    @JsonbProperty("ZsAvg")
    private BigDecimal zsAvg;

    @ApiModelProperty(value = "污染物指定时间内最大折算值", name = "ZsMax")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("ZsMax")
    @JsonbProperty("ZsMax")
    private BigDecimal zsMax;

    @ApiModelProperty(value = "监测污染物实时数据标记", name = "Flag", allowableValues = "[P,F,C,M,T,D,S,N,0,1,2,3]")
    @Pattern(regexp = "[A-Z0123]")
    @JsonProperty("Flag")
    @JsonbProperty("Flag")
    private String flag;

    @ApiModelProperty(value = "污染物指定时间内累计值", name = "Cou")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("Cou")
    @JsonbProperty("Cou")
    private BigDecimal cou;

    @ApiModelProperty(value = "设备运行状态的实时采样值", name = "RS")
//    @Pattern(regexp = "\\d")
    @JsonProperty("RS")
    @JsonbProperty("RS")
    private BigDecimal rs;

    @ApiModelProperty(value = "设备指定时间内的运行时间", name = "RT")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @DecimalMax("24")
    @DecimalMin("0")
    @Digits(integer = 24, fraction = 99)
    @JsonProperty("RT")
    @JsonbProperty("RT")
    private BigDecimal rt;

    @ApiModelProperty(value = "污染物报警期间内采样值", name = "Ala")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("Ala")
    @JsonbProperty("Ala")
    private BigDecimal ala;

    @ApiModelProperty(value = "污染物报警上限值", name = "UpValue")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("UpValue")
    @JsonbProperty("UpValue")
    private BigDecimal upValue;

    @ApiModelProperty(value = "污染物报警下限值", name = "LowValue")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("LowValue")
    @JsonbProperty("LowValue")
    private BigDecimal lowValue;

    @ApiModelProperty(value = "噪声监测日历史数据", name = "Data")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("Data")
    @JsonbProperty("Data")
    private BigDecimal data;

    @ApiModelProperty(value = "噪声昼间历史数据", name = "DayData")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("DayData")
    @JsonbProperty("DayData")
    private BigDecimal dayData;

    @ApiModelProperty(value = "噪声夜间历史数据", name = "NightData")
//    @Pattern(regexp = "[+-]\\d{1,14}\\.\\d{2}")
    @JsonProperty("NightData")
    @JsonbProperty("NightData")
    private BigDecimal nightData;

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

    public BigDecimal getCou() {
        return cou;
    }

    public void setCou(BigDecimal cou) {
        this.cou = cou;
    }

    public BigDecimal getRs() {
        return rs;
    }

    public void setRs(BigDecimal rs) {
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

    public BigDecimal getData() {
        return data;
    }

    public void setData(BigDecimal data) {
        this.data = data;
    }

    public BigDecimal getDayData() {
        return dayData;
    }

    public void setDayData(BigDecimal dayData) {
        this.dayData = dayData;
    }

    public BigDecimal getNightData() {
        return nightData;
    }

    public void setNightData(BigDecimal nightData) {
        this.nightData = nightData;
    }
}
