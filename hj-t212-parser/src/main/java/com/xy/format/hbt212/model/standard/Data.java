package com.xy.format.hbt212.model.standard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.model.verify.groups.Group;
import io.swagger.annotations.ApiModelProperty;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 数据段
 * Created by xiaoyao9184 on 2017/12/19.
 */
public class Data {

    public static String CP = "CP";
    public static String FLAG = "Flag";


    @ApiModelProperty(value = "请求编号", name = "QN")
    @ValidDate(format = "yyyyMMddHHmmssSSS")
    @JsonProperty("QN")
    @JsonbProperty("QN")
    private String qn;

    @ApiModelProperty(value = "总包号", name = "PNUM")
    @Max(value = 9999, groups = Group.Version.V2005.class)
    @Max(value = 999999999, groups = Group.Version.V2017.class)
    @Min(value = 1, groups = Group.SubPack.class)
    @JsonProperty("PNUM")
    @JsonbProperty("PNUM")
    private Integer pNum;

    @ApiModelProperty(value = "包号", name = "PNO")
    @Max(value = 9999, groups = Group.Version.V2005.class)
    @Max(value = 99999999, groups = Group.Version.V2017.class)
    @Min(value = 1, groups = Group.SubPack.class)
    @JsonProperty("PNO")
    @JsonbProperty("PNO")
    private Integer pNo;

    @ApiModelProperty(value = "系统编号", name = "ST")
    @C(len = 5, groups = Group.Version.V2005.class)
    @C(len = 5, groups = Group.Version.V2017.class)
    @JsonProperty("ST")
    @JsonbProperty("ST")
    private String st;

    @ApiModelProperty(value = "命令编号", name = "CN")
    @C(len = 7, groups = Group.Version.V2005.class)
    @C(len = 7, groups = Group.Version.V2017.class)
    @JsonProperty("CN")
    @JsonbProperty("CN")
    private String cn;

    @ApiModelProperty(value = "访问密码", name = "PW")
    @C(len = 6, groups = Group.Version.V2005.class)
    @C(len = 9, groups = Group.Version.V2017.class)
    @JsonProperty("PW")
    @JsonbProperty("PW")
    private String pw;

    @ApiModelProperty(value = "设备唯一标识", name = "MN")
    @C(len = 14, groups = Group.Version.V2005.class)
    @C(len = 24, groups = Group.Version.V2017.class)
    @JsonProperty("MN")
    @JsonbProperty("MN")
    private String mn;

    @ApiModelProperty(value = "是否拆分包及应答标志", name = "Flag")
    @JsonProperty("Flag")
    @JsonbProperty("Flag")
    private List<DataFlag> dataFlag;

    @ApiModelProperty(value = "指令参数", name = "CP")
    @Valid
    @JsonProperty("CP")
    @JsonbProperty("CP")
    private CpData cp;

    public String getQn() {
        return qn;
    }

    public void setQn(String qn) {
        this.qn = qn;
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
        this.pNum = pNum;
    }

    public Integer getpNo() {
        return pNo;
    }

    public void setpNo(Integer pNo) {
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

    public List<DataFlag> getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(List<DataFlag> dataFlag) {
        this.dataFlag = dataFlag;
    }

    public CpData getCp() {
        return cp;
    }

    public void setCp(CpData cp) {
        this.cp = cp;
    }

}
