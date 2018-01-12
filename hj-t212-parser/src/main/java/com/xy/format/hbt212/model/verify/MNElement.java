package com.xy.format.hbt212.model.verify;

@Deprecated
public enum MNElement {
    HEADER(8),
    COMPANY(28),
    OBJECT(24),
    NUMBER(36);

    private int len;

    MNElement(int len){
        this.len = len;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
