package com.xy.format.hbt212.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
@ApiModel
public class Segment {

    @ApiModelProperty(value = "请求编号", name = "QN")
    @Pattern(regexp = "\\d{20}")
    @JsonProperty("QN")
    @JsonbProperty("QN")
    private String qn;

    @ApiModelProperty(value = "总包号", name = "PNUM")
    @Pattern(regexp = ".{4}")
    @JsonProperty("PNUM")
    @JsonbProperty("PNUM")
    private String pNum;

    @ApiModelProperty(value = "包号", name = "PNO")
    @Pattern(regexp = ".{4}")
    @JsonProperty("PNO")
    @JsonbProperty("PNO")
    private String pNo;

    @ApiModelProperty(value = "系统编号", name = "ST")
    @Pattern(regexp = ".{0,5}")
    @JsonProperty("ST")
    @JsonbProperty("ST")
    private String st;

    @ApiModelProperty(value = "命令编号", name = "CN")
    @Pattern(regexp = ".{0,7}")
    @JsonProperty("CN")
    @JsonbProperty("CN")
    private String cn;

    @ApiModelProperty(value = "访问密码", name = "PW")
    @Pattern(regexp = ".{6}")
    @JsonProperty("PW")
    @JsonbProperty("PW")
    private String pw;

    @ApiModelProperty(value = "设备唯一标识", name = "MN")
    @Pattern(regexp = ".{14}")
    @JsonProperty("MN")
    @JsonbProperty("MN")
    private String mn;

    @ApiModelProperty(value = "是否拆分包及应答标志", name = "Flag")
    @JsonProperty("Flag")
    @JsonbProperty("Flag")
    private List<SegmentFlag> segmentFlag;

    @ApiModelProperty(value = "指令参数", name = "CP")
    @Pattern(regexp = ".{0,960}")
//    @JsonProperty("CP")
//    @JsonbProperty("CP")
    private String cp;

    public String getQn() {
        return qn;
    }

    public void setQn(String qn) {
        this.qn = qn;
    }

    public String getpNum() {
        return pNum;
    }

    public void setpNum(String pNum) {
        this.pNum = pNum;
    }

    public String getpNo() {
        return pNo;
    }

    public void setpNo(String pNo) {
        this.pNo = pNo;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public List<SegmentFlag> getSegmentFlag() {
        return segmentFlag;
    }

    public void setSegmentFlag(List<SegmentFlag> segmentFlag) {
        this.segmentFlag = segmentFlag;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
