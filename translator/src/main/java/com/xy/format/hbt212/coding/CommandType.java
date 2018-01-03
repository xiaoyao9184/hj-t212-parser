package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMatch;
import com.xy.format.hbt212.CodeMean;
import com.xy.format.hbt212.CodePattern;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * 命令类别
 * 6.6.5.1 类别划分
 * Created by xiaoyao9184 on 2017/12/30.
 */
public enum CommandType implements CodeMean, CodePattern, CodeMatch {

    REQUEST("请求命令","[1]\\d{3}"),
    UPLOAD("上传命令","[2]\\d{3}"),
    NOTICE("通知命令","[3]\\d{3}"),
    SHELL("交互命令","[9]\\d{3}"),
    UNKNOW("未知","\\d{4}",10000);

    private String code;
    private String meaning;
    private String pattern;
    private Predicate<String> predicate;
    private int order;

    CommandType(String meaning,String pattern){
        this.code = name();
        this.meaning = meaning;
        this.pattern = pattern;
        this.predicate = Pattern.compile(this.pattern).asPredicate();
        this.order = ordinal();
    }

    CommandType(String meaning,String pattern,int order){
        this.code = name();
        this.meaning = meaning;
        this.pattern = pattern;
        this.predicate = Pattern.compile(this.pattern).asPredicate();
        this.order = order;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String mean() {
        return meaning;
    }

    @Override
    public String pattern() {
        return pattern;
    }

    @Override
    public int order() {
        return order;
    }

    @Override
    public boolean match(String code) {
        return predicate.test(code);
    }

}
