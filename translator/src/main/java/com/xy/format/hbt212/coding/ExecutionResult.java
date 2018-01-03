package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMean;

/**
 * 执行结果定义（可扩充）
 * Created by xiaoyao9184 on 2017/12/30.
 */
public enum ExecutionResult implements CodeMean {

    _1("执行成功"),
    _2("执行失败，但不知道原因"),
    _3("命令请求条件错误"),
    _4("通讯超时"),
    _5("系统繁忙不能执行"),
    _6("系统故障"),
    _100("没有数据");

    private String code;
    private String meaning;

    ExecutionResult(String meaning){
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
