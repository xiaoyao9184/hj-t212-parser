package com.xy.format.hbt212.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xy.format.hbt212.model.verify.groups.VersionGroup;
import io.swagger.annotations.ApiModelProperty;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import java.math.BigDecimal;

/**
 * 污染治理设施
 * Created by xiaoyao9184 on 2018/1/11.
 */
public class Device {

    @ApiModelProperty(value = "污染治理设施运行状态的实时采样值", name = "RS")
    @Max(value = 5, groups = VersionGroup.V2017.class)
//    @N(integer = 1, groups = VersionGroup.V2017.class)
    @JsonProperty("RS")
    @JsonbProperty("RS")
    private int rs;

    @ApiModelProperty(value = "污染治理设施一日内的运行时间", name = "RT")
    @DecimalMax(value = "24", groups = VersionGroup.V2017.class)
//    @N(integer = 2, fraction = 2, groups = VersionGroup.V2017.class)
    @JsonProperty("RT")
    @JsonbProperty("RT")
    private BigDecimal rt;

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public BigDecimal getRt() {
        return rt;
    }

    public void setRt(BigDecimal rt) {
        this.rt = rt;
    }
}
