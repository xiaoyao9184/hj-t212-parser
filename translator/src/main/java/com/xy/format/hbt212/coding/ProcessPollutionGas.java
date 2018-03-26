package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMatch;
import com.xy.format.hbt212.CodeMean;
import com.xy.format.hbt212.CodePattern;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by xiaoyao9184 on 2018/3/26.
 */
public enum ProcessPollutionGas implements CodeMean, CodePattern, CodeMatch {

    g101xx("增压风机状态", "无量纲", "N1"),
    g102xx("增压风机电流", "安[培]", "N4.2"),
    g103xx("浆液循环泵状态", "无量纲", "N1"),
    g104xx("浆液循环泵电流", "安[培]", "N4.2"),
    g105xx("密封剂状态", "无量纲", "N1"),
    g106xx("密封剂电流", "安[培]", "N4.2"),
    g107xx("GGH运行状态", "无量纲", "N1"),
    g108xx("GGH电机电流", "安[培]", "N4.2"),
    g109xx("浆液泵状态", "无量纲", "N1"),
    g110xx("浆液泵流量", "立方米/小时", "N4.3"),
    g111xx("脱硫塔内浆液pH", "无量纲", "N2.2"),
    g112xx("吸收塔除雾器状态", "无量纲", "N1"),
    g113xx("吸收塔除物器电流", "安[培]", "N4.2"),
    g114xx("吸收塔搅拌器状态", "无量纲", "N1"),
    g115xx("吸收塔浆液密度", "千克/立方米", "N3.3"),
    g116xx("旁路挡板门开度", "[角]度", "N4"),
    g117xx("石膏排除泵状态", "无量纲", "N1"),
    g118xx("石膏排除泵电流", "安[培]", "N4.2"),
    g119xx("脱硫率", "%", "N3.2"),
    g201xx("脱硫塔内喷水泵电流", "安[培]", "N4.2"),
    g202xx("脱硫剂输送装置", "安[培]", "N4.2"),
    g203xx("称重给煤机计量信号", "无量纲", "N1"),
    g204xx("炉膛床压", "帕", "N7"),
    g205xx("炉膛床温", "摄氏度", "N5.1"),
    g206xx("冷渣器转速", "转/分钟", "N6"),
    g207xx("返料风机电流", "安[培]", "N4.2"),
    g208xx("引风机电流", "安[培]", "N4.2"),
    g209xx("一次风机电流", "安[培]", "N4.2"),
    g210xx("二次风机电流", "安[培]", "N4.2"),
    g211xx("石灰石给料机电流", "安[培]", "N4.2"),
    g212xx("脱硫率", "%", "N3.1"),
    g301xx("氨喷射系统电流", "安[培]", "N4.2"),
    g302xx("稀释风机状态", "无量纲", "N1"),
    g303xx("稀释风机电流", "安[培]", "N4.2"),
    g304xx("氨泵风机状态", "无量纲", "N1"),
    g305xx("氨泵风机电流", "安[培]", "N4.2"),
    g306xx("旁路挡板状态", "无量纲", "N1"),
    g307xx("旁路挡板开度", "[角]度", "N4"),
    g308xx("旁路挡板左右压差", "千帕", "N5.3"),
    //协议冲突
    _g119xx("入口二氧化硫SO2", "毫克/立方米", "N3.3"),
    g120xx("入口氮氧化物NOx", "毫克/立方米", "N3.3"),
    g121xx("入口含氧量O2", "%", "N3.1"),
    g122xx("入口流量", "立方米/小时", "N4.3"),
    g123xx("入口温度", "摄氏度", "N3.1"),
    g124xx("入口烟尘", "毫克/立方米", "N3.3"),
    g125xx("入口压力", "千帕", "N5.3"),
    g126xx("入口湿度", "%", "N3.1"),
    g127xx("出口二氧化硫SO2", "毫克/立方米", "N3.3"),
    g128xx("出口氮氧化物NOx", "毫克/立方米", "N3.3"),
    g129xx("出口含氧量O2", "%", "N3.1"),
    g130xx("出口流量", "立方米/小时", "N4.3"),
    g131xx("出口温度", "摄氏度", "N3.1"),
    g132xx("出口烟尘", "毫克/立方米", "N3.3"),
    g133xx("出口压力", "千帕", "N5.3"),
    g134xx("出口湿度", "%", "N3.1");

    private String code;
    private String meaning;
    private String unit;
    private String type;
    private String pattern;
    private int order;
    private Predicate<String> predicate;


    ProcessPollutionGas(String meaning, String unit, String type) {
        this.code = name();
        this.meaning = meaning;
        this.unit = unit;
        this.type = type;
        this.pattern = this.code.replace("xx", "\\d{1,2}");
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
