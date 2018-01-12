package com.xy.format.hbt212.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.model.verify.groups.VersionGroup;
import io.swagger.annotations.ApiModelProperty;

import javax.json.bind.annotation.JsonbProperty;

/**
 * 现场端
 * Created by xiaoyao9184 on 2018/1/11.
 */
public class LiveSide {

    @ApiModelProperty(value = "现场端信息", name = "Info")
    @JsonProperty("Info")
    @JsonbProperty("Info")
    private String info;

    @ApiModelProperty(value = "在线监控（监测）仪器仪表编码", name = "SN")
    @C(len = 24, groups = VersionGroup.V2017.class)
    @JsonProperty("SN")
    @JsonbProperty("SN")
    private String sn;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
