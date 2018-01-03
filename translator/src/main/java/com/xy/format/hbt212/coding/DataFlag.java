package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMean;

/**
 * 数据标记（可扩充）
 * Created by xiaoyao9184 on 2017/12/30.
 */
public enum DataFlag implements CodeMean {

    N("在线监控（监测）仪器仪表工作正常"),
    F("在线监控（监测）仪器仪表停运"),
    M("在线监控（监测）仪器仪表处于维护期间产生的数据"),
    S("手工输入的设定值"),
    D("在线监控（监测）仪器仪表故障"),
    C("在线监控（监测）仪器仪表处于校准状态"),
    T("在线监控（监测）仪器仪表采样数值超过测量上限"),
    B("在线监控（监测）仪器仪表与数采仪通讯异常");

    private String code;
    private String meaning;

    DataFlag(String meaning){
        this.code = name().substring(1);
        this.meaning = meaning;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String mean() {
        return meaning;
    }
}
