package com.xy.format.hbt212.coding;

import com.xy.format.hbt212.CodeMean;

/**
 * Created by xiaoyao9184 on 2018/3/26.
 */
public enum LiveSideMessage implements CodeMean {

    i11001("运行日志", "--", "C890", "日志信息在“//”之间, 使用 UTF-8 编码",
            LiveSideDeviceType._1, LiveSideMessageType._1),

    i12001("工作状态", "无量纲", "N2", "运行（0）、维护（1）、故障（2）、校准（3）、反吹（5）、电源故障（6）、测量（7）、标定（8）、待机（9）、运维（10）",
            LiveSideDeviceType._1, LiveSideMessageType._2),
    i12002("分析仪与数采仪通讯状态", "无量纲", "N1", "正常（0）、异常（1）",
            LiveSideDeviceType._1, LiveSideMessageType._2),
    i12003("COD 分析仪报警状态", "无量纲", "N1", "正常（0）、异常（1）",
            LiveSideDeviceType._1, LiveSideMessageType._2),

    i13001("测量量程", "--", "--" , "单位、数据类型根据实际自定义",
            LiveSideDeviceType._1, LiveSideMessageType._3),
    i13002("测量精度", "--", "--" , "单位、数据类型根据实际自定义",
            LiveSideDeviceType._1, LiveSideMessageType._3),
    i13003("测量间隔", "分钟", "N4",
            LiveSideDeviceType._1, LiveSideMessageType._3),
    i13004("消解温度", "摄氏度", "N3.1",
            LiveSideDeviceType._1, LiveSideMessageType._3),
    i13005("消解时长", "分钟", "N2",
            LiveSideDeviceType._1, LiveSideMessageType._3),
    i13006("校准时间", "年月日时分秒", "YYYYMMDD HHMMSS",
            LiveSideDeviceType._1, LiveSideMessageType._3),
    i13007("截距", "--", "--" , "单位、数据类型根据实际自定义",
            LiveSideDeviceType._1, LiveSideMessageType._3),
    i13008("斜率", "--", "--" , "单位、数据类型根据实际自定义",
            LiveSideDeviceType._1, LiveSideMessageType._3),
    i13009("测量检出限", "--", "--" , "单位、数据类型根据实际自定义",
            LiveSideDeviceType._1, LiveSideMessageType._3),


    i21001("运行日志", "--", "C890", "日志信息在“//”之间, 使用 UTF-8 编码",
            LiveSideDeviceType._2, LiveSideMessageType._1),

    i22001("工作状态", "无量纲", "N1", "运行（0）、停机（1）、 故障（2）、维护（3）",
            LiveSideDeviceType._2, LiveSideMessageType._2),
    i22002("用户状态", "无量纲", "N1", "普通用户（0）、管理员（1）、维护人员（2）",
            LiveSideDeviceType._2, LiveSideMessageType._2),
    i22003("数采仪与上位机通讯状态", "无量纲", "N1" ,"正常（0）、异常（1）",
            LiveSideDeviceType._2, LiveSideMessageType._2),
    i22101("数采仪通道通讯状态", "无量纲", "N1", "正常（0）、异常（1）、通道未接设备（2）数采仪（参数）",
            LiveSideDeviceType._2, LiveSideMessageType._2),

    i23001("本地大气压力", "千帕", "N3.3", "辅助设备（日志）",
            LiveSideDeviceType._2, LiveSideMessageType._3),

    i31001("门禁日志", "--", "C890", "日志信息在“//”之间, 使用 UTF-8编码辅助设备（状态）",
            LiveSideDeviceType._3, LiveSideMessageType._1),

    i32001("门禁状态", "无量纲", "N1", "运行（0）、停机（1）、故障（2）、维护（3）辅助设备（参数）",
            LiveSideDeviceType._3, LiveSideMessageType._2),

    i33001("CEMS 伴热管温度", "摄氏度" , "N4",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33002("CEMS 冷凝温度", "摄氏度", "N1.1",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33101("监测站房温度", "摄氏度", "N4",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33102("监测站房湿度", "摄氏度", "N4",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33103("监测站房电压", "伏[特]", "N4",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33104("监测站房原水压力", "千帕", "N6",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33105("监测站房进样压力 1", "千帕", "N6",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33106("监测站房进样压力 2", "千帕", "N6",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33107("沉砂池清洗时间", "秒", "N4",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33200("污水处理站（厂）电流量", "安 [培]", "N4.2",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33201("污水处理站（厂）累计耗电量", "千瓦[特][小]时", "N13",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i33202("污水处理站（厂）日耗电量", "千瓦[特][小]时", "N7",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i3331x("炉膛内上部焚烧温度", "摄氏度", "N4.1", "x 为设备编号（0-9）,可以根据测点数量扩充;测量数值变化或者以固定时间间隔上传",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i3332x("炉膛内中部焚烧温度", "摄氏度", "N4.1",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i3333x("炉膛内下部焚烧温度", "摄氏度", "N4.1",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    i3334x("炉膛内二次空气喷入点温度", "摄氏度", "N4.1",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    I33400("生产负荷", "%", "N3.1", "优先由企业 DCS 中接入，如果 DCS 没有，从传感器接入",
            LiveSideDeviceType._3, LiveSideMessageType._3),
    ;

    private String code;
    private String meaning;
    private String unit;
    private String type;
    private String remark;
    private LiveSideDeviceType deviceType;
    private LiveSideMessageType messageType;

    LiveSideMessage(String meaning, String unit, String type, LiveSideDeviceType deviceType, LiveSideMessageType messageType){
        this.code = name();
        this.meaning = meaning;
        this.unit = unit;
        this.type = type;
        this.deviceType = deviceType;
        this.messageType = messageType;
    }

    LiveSideMessage(String meaning, String unit, String type, String remark, LiveSideDeviceType deviceType, LiveSideMessageType messageType){
        this.code = name();
        this.meaning = meaning;
        this.unit = unit;
        this.type = type;
        this.remark = remark;
        this.deviceType = deviceType;
        this.messageType = messageType;
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


    public String remark() {
        return remark;
    }
    public LiveSideDeviceType deviceType() {
        return deviceType;
    }
    public LiveSideMessageType messageType() {
        return messageType;
    }

}
