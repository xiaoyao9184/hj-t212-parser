package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMean;

/**
 * Created by xiaoyao9184 on 2018/3/26.
 */
public enum LiveSideDeviceType implements CodeMean {

    _1("在线监控（监测）仪器仪表","1"),
    _2("数据采集传输仪","2"),
    _3("辅助设备","3"),
    _4("预留扩充","4"),
    _5("预留扩充","5")
    ;

    private String code;
    private String meaning;


    LiveSideDeviceType(String meaning, String code){
        this.code = code;
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
