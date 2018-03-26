package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMean;

/**
 * Created by xiaoyao9184 on 2018/3/26.
 */
public enum ProcessCraftsGas implements CodeMean {

    _1("脱硫设施","湿法脱硫（石灰石/石灰-石膏法）","1"),
    _2("脱硫设施","半干法脱硫（循环硫化床法）","2"),
    _3("脱硝设施","SCR","3"),
    _4("脱硝设施","SNCR","4"),
    _5("除尘","电除尘","5"),
    _6("除尘","布袋除尘","6"),
    _10("预留扩充", "", "a-b")
    ;

    private String code;
    private String meaning;
    private String type;

    ProcessCraftsGas(String type, String meaning, String code){
        this.code = code;
        if(meaning == null){
            this.meaning = type;
        }else{
            this.meaning = meaning;
        }
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

    public String type() {
        return type;
    }
}
