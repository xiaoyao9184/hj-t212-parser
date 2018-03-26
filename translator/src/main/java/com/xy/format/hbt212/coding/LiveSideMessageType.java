package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMean;

/**
 * Created by xiaoyao9184 on 2018/3/26.
 */
public enum LiveSideMessageType implements CodeMean {

    _1("日志","1"),
    _2("状态","2"),
    _3("参数","3"),
    _4("预留扩充","4"),
    _5("预留扩充","5")
    ;

    private String code;
    private String meaning;


    LiveSideMessageType(String meaning, String code){
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
