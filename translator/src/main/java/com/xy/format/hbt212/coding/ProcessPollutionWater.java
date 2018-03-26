package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMatch;
import com.xy.format.hbt212.CodeMean;
import com.xy.format.hbt212.CodePattern;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by xiaoyao9184 on 2018/3/26.
 */
public enum ProcessPollutionWater implements CodeMean, CodePattern, CodeMatch {

    e101xx("进水口流量","升/秒","N6.2"),
    e102xx("进水口COD","毫克/升","N5.1"),
    e103xx("进水口氨氮","毫克/升","N3.2"),
    e104xx("进水口总磷","毫克/升","N3.2"),
    e105xx("进水口总氮","毫克/升","N5.1"),
    e106xx("进水口pH","无量纲","N2.2"),
    e201xx("出水口流量","升/秒","N6.2"),
    e202xx("出水口COD","毫克/升","N5.1"),
    e203xx("出水口氨氮","毫克/升","N3.2"),
    e204xx("出水口总磷","毫克/升","N3.2"),
    e205xx("出水口PH","无量纲","N2.2"),
    e301xx("污水提升泵","安[培]","N4.2"),
    e302xx("鼓风机","安[培]","N4.2"),
    e303xx("鼓风量","毫克/升","N7"),
    e304xx("生化池污泥浓度","毫克/升","N5.1"),
    e305xx("生化池溶解氧浓度","毫克/升","N5.1"),
    e306xx("污泥剩余泵","安[培]","N4.2"),
    e307xx("污泥回流泵","安[培]","N4.2"),
    e308xx("污泥回流量","千克","N7"),
    e309xx("污泥剩余量","千克","N7"),
    e310xx("污泥压滤机","安[培]","N4.2"),
    e311xx("阀门状态","无量纲","N1"),
    e312xx("储泥池液位","米","N2.3"),
    e313xx("加药量","毫克/升","N5.1"),
    e314xx("生化池氧化还原电位","毫伏[特]","N7"),
    e401xx("污水提升泵","安[培]","N4.2"),
    e402xx("曝气设备","安[培]","N4.2"),
    e403xx("生化池污泥浓度","毫克/升","N5.1"),
    e404xx("厌氧池溶解氧浓度","毫克/升","N5.1"),
    e405xx("缺氧池溶解氧浓度","毫克/升","N5.1"),
    e406xx("好氧池溶解氧浓度","毫克/升","N5.1"),
    e407xx("污泥剩余泵","安[培]","N4.2"),
    e408xx("污泥回流泵","安[培]","N4.2"),
    e409xx("污泥回流量","千克","N4.2"),
    e410xx("污泥剩余量","千克","N4.2"),
    e411xx("污泥压滤机","安[培]","N4.2"),
    e412xx("搅拌器转态","无量纲","N1"),
    e413xx("阀门状态","无量纲","N1"),
    e414xx("缺氧池氧化还原电位","毫伏[特]","N7"),
    e415xx("好氧池氧化还原电位","毫伏[特]","N7"),
    e416xx("提升泵池液位","米","N2.3"),
    e417xx("储泥池液位","米","N2.3"),
    e418xx("加药量","毫克/升","N5.1"),
    e501xx("污水提升泵","安[培]","N4.2"),
    e502xx("曝气设备","安[培]","N4.2"),
    e503xx("供气量状态","无量纲","N1"),
    e504xx("生化池污泥浓度","毫克/升","N5.1"),
    e505xx("厌氧池溶解氧浓度","毫克/升","N5.1"),
    e506xx("缺氧池溶解氧浓度","毫克/升","N5.1"),
    e507xx("好氧池溶解氧浓度","毫克/升","N5.1"),
    e508xx("混合液回流泵","安[培]","N4.2"),
    e509xx("剩余污泥泵","安[培]","N4.2"),
    e510xx("剩余污泥量","千克","N7"),
    e511xx("搅拌器状态","无量纲","N1"),
    e512xx("阀门状态","无量纲","N1"),
    e513xx("缺氧池氧化还原电位","毫伏[特]","N7"),
    e514xx("好氧池氧化还原电位","毫伏[特]","N7"),
    e515xx("提升泵池液位","米","N2.3"),
    e516xx("储泥池液位","米","N2.3"),
    e517xx("加药量","毫克/升","N5.1"),
    e601xx("污水提升泵","安[培]","N4.2"),
    e602xx("曝气设备","安[培]","N4.2"),
    e603xx("SBR池污泥浓度","毫克/升","N5.1"),
    e604xx("SBR池溶解氧浓度","毫克/升","N5.1"),
    e605xx("污泥剩余泵","安[培]","N4.2"),
    e606xx("污泥回流泵","安[培]","N4.2"),
    e607xx("污泥回流量","千克","N7"),
    e608xx("污泥剩余量","千克","N7"),
    e609xx("污泥压滤机","安[培]","N4.2"),
    e610xx("搅拌器","安[培]","N4.2"),
    e614xx("SBR池曝气搅拌时氧化还原电位","毫伏[特]","N7"),
    e615xx("阀门状态","无量纲","N1"),
    e616xx("提升泵池液位","米","N2.3"),
    e617xx("储泥池液位","米","N2.3"),
    e701xx("污水提升泵","安[培]","N4.2"),
    e702xx("曝气设备","安[培]","N4.2"),
    e703xx("接触氧化池污泥浓度","毫克/升","N5.1"),
    e704xx("接触氧化池溶解氧浓度","毫克/升","N5.1"),
    e705xx("剩余污泥泵","安[培]","N4.2"),
    e706xx("剩余污泥量","千克","N7"),
    e707xx("污泥压滤机","安[培]","N4.2"),
    e708xx("阀门状态","无量纲","N1"),
    e709xx("提升泵池液位","米","N2.3"),
    e710xx("储泥池液位","米","N2.3"),

    //协议里有错误，与e701xx冲突，猜测应该是e711xx
    e711xx("加药量","毫克/升","N5.1"),
    e801xx("污水提升泵","安[培]","N4.2"),
    e802xx("曝气设备","安[培]","N4.2"),
    e803xx("污泥浓度","毫克/升","N5.1"),
    e804xx("溶解氧浓度","毫克/升","N5.1"),
    e805xx("剩余污泥泵","安[培]","N4.2")
    ;

    private String code;
    private String meaning;
    private String unit;
    private String type;
    private String pattern;
    private int order;
    private Predicate<String> predicate;


    ProcessPollutionWater(String meaning, String unit, String type){
        this.code = name().substring(1);
        this.meaning = meaning;
        this.unit = unit;
        this.type = type;
        this.pattern = this.code.replace("xx", "\\d{2}");
        this.order = ordinal();
        this.predicate = Pattern.compile(this.pattern).asPredicate();
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


    public String unit() {
        return unit;
    }
    public String type() {
        return type;
    }
}
