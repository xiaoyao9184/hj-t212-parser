package com.xy.format.hbh212.model;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class Pack {
    private char[] header = new char[]{ '#','#' };
    private char[] length = new char[4];
    private char[] segment = new char[1024];
    private char[] crc = new char[4];
    private char[] footer = new char[]{ '\r', '\n' };

    public char[] getHeader() {
        return header;
    }

    public void setHeader(char[] header) {
        this.header = header;
    }

    public char[] getLength() {
        return length;
    }

    public void setLength(char[] length) {
        this.length = length;
    }

    public char[] getSegment() {
        return segment;
    }

    public void setSegment(char[] segment) {
        this.segment = segment;
    }

    public char[] getCrc() {
        return crc;
    }

    public void setCrc(char[] crc) {
        this.crc = crc;
    }

    public char[] getFooter() {
        return footer;
    }

    public void setFooter(char[] footer) {
        this.footer = footer;
    }
}
