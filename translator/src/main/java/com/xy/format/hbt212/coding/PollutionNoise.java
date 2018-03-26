package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMean;

/**
 * Created by xiaoyao9184 on 2018/3/26.
 */
public enum PollutionNoise implements CodeMean {

    LA("A权声级","分贝","N3.1"),
    L5("累计百分声级L5","分贝","N3.1"),
    L10("累计百分声级L10","分贝","N3.1"),
    L50("累计百分声级L50","分贝","N3.1"),
    L90("累计百分声级L90","分贝","N3.1"),
    L95("累计百分声级L95","分贝","N3.1"),
    Leq("等效声级","分贝","N3.1"),
    Ldn("昼夜等效声级","分贝","N3.1"),
    Ld("昼间等效声级","分贝","N3.1"),
    Ln("夜间等效声级","分贝","N3.1"),
    LMx("最大的瞬时声级","分贝","N3.1"),
    LMn("最小的瞬时声级","分贝","N3.1"),
    ;

    private String code;
    private String meaning;
    private String unit;
    private String type;

    PollutionNoise(String meaning, String unit, String type){
        this.code = name();
        this.meaning = meaning;
        this.unit = unit;
        this.type = type;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String mean() {
        return meaning;
    }

    public String unit() {
        return unit;
    }
    public String type() {
        return type;
    }
}
