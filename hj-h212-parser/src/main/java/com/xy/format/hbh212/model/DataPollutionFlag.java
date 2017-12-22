package com.xy.format.hbh212.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
@ApiModel
public enum DataPollutionFlag {
    @ApiModelProperty(value = "电源故障")
    P,
    @ApiModelProperty(value = "排放源停运")
    F,
    @ApiModelProperty(value = "校验")
    C,
    @ApiModelProperty(value = "维护")
    M,
    @ApiModelProperty(value = "超测上限")
    T,
    @ApiModelProperty(value = "故障")
    D,
    @ApiModelProperty(value = "设定值")
    S,
    @ApiModelProperty(value = "正常")
    N,
    @ApiModelProperty(value = "空气检测站：校准数据")
    _0("0"),
    @ApiModelProperty(value = "空气检测站：气象参数")
    _1("1"),
    @ApiModelProperty(value = "空气检测站：异常数据")
    _2("2"),
    @ApiModelProperty(value = "空气检测站：正常数据")
    _3("3");

    private String key;

    DataPollutionFlag(){
        this.key = name();
    }

    DataPollutionFlag(String key){
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
