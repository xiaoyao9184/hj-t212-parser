package com.xy.format.hbt212.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

/**
 * 数据段 标志
 * Created by xiaoyao9184 on 2017/12/15.
 */
@ApiModel
public enum DataFlag {
    @ApiModelProperty(value = "命令是否应答")
    A(1),
    @ApiModelProperty(value = "是否有数据包序号")
    D(2),
    @ApiModelProperty(value = "标准版本号V0（HJ 212-2017）")
    V0,
    @ApiModelProperty(value = "标准版本号V1")
    V1,
    @ApiModelProperty(value = "标准版本号V2")
    V2,
    @ApiModelProperty(value = "标准版本号V3")
    V3,
    @ApiModelProperty(value = "标准版本号V4")
    V4,
    @ApiModelProperty(value = "标准版本号V5")
    V5;

    private int bit;

    DataFlag(){
        this.bit = (1 << ordinal());
    }

    DataFlag(int bit){
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public boolean isMarked(int flags) {
        return (flags & bit) != 0;
    }

    public boolean isMarked(Collection<DataFlag> flags) {
        return flags != null && flags.contains(this);
    }

}
