package com.xy.format.hbt212.model.schema;

/**
 * segment element
 * Created by xiaoyao9184 on 2017/12/19.
 */
public enum SegmentSchema {
    QN(0,20),
    PNUM(4),
    PNO(4),
    ST(0,5),
    CN(0,7),
    PW(6),
    MN(14),
    Flag(0,3),
    CP(0,960);

    private int min;
    private int max;

    SegmentSchema(int min, int max){
        this.min = min;
        this.max = max;
    }

    SegmentSchema(int len){
        this.min = len;
        this.max = len;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
