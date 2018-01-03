package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMean;

/**
 * 请求命令返回（可扩充）
 * Created by xiaoyao9184 on 2017/12/30.
 */
public enum CommandResult implements CodeMean {

    _1("准备执行请求"),
    _2("请求被拒绝"),
    _3("PW 错误"),
    _4("MN 错误"),
    _5("ST 错误"),
    _6("Flag 错误"),
    _7("QN 错误"),
    _8("CN 错误"),
    _9("CRC 校验错误"),
    _100("未知错误");

    private String code;
    private String meaning;

    CommandResult(String meaning){
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
