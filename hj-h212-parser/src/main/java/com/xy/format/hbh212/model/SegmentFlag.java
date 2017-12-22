package com.xy.format.hbh212.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
@ApiModel
public enum SegmentFlag {
    @ApiModelProperty(value = "数据是否应答")
    A(0x01),
    @ApiModelProperty(value = "是否有数据序号")
    D(0x02);

    private int bit;

    SegmentFlag(int bit){
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }
}
