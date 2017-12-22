package com.xy.format.hbh212.model.schema;

/**
 * pack element
 * Created by xiaoyao9184 on 2017/12/19.
 */
public enum PackSchema {
    HEADER(2),
    SEGMENT_LEN(4),
    SEGMENT(-0),
    CRC(4),
    FOOTER(2);

    private int len;

    PackSchema(int len){
        this.len = len;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
