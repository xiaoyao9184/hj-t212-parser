package com.xy.format.hbt212.exception;

import com.xy.format.hbt212.model.verify.PacketElement;
import com.xy.format.segment.exception.SegmentFormatException;

/**
 * Created by xiaoyao9184 on 2017/12/19.
 */
public class T212FormatException extends Exception {

    private Object result;

    public T212FormatException(String message) {
        super(message);
    }

    public T212FormatException(String message,Object result) {
        super(message);
        this.result = result;
    }

    public T212FormatException(String message,Throwable t) {
        super(message,t);
    }


    public static void separator_position(char c, Enum<?> mode) throws T212FormatException {
        throw new T212FormatException("Separator position is wrong: " + c + " cant in Mode: " + mode.name());
    }

    public static void static_data_match(Enum<?> flag, char[] tar, char[] src) throws T212FormatException {
        throw new T212FormatException("Static data core: " + flag.toString() + ": " + new String(tar) + " -> " + new String(src));
    }

    public static void length_not_match(Enum<?> flag, int tar, int src) throws T212FormatException {
        throw new T212FormatException("Length does not core: " + flag.toString() + ": " + tar + " -> " + src);
    }

    public static void length_not_range(Enum<?> flag, int src, int min, int max) throws T212FormatException {
        throw new T212FormatException("Length does not in range: " + flag.toString() + ": " + src + " -> (" + min + "," + max + ")");
    }

    public static void field_is_missing(Enum<?> flag, String field) throws T212FormatException {
        throw new T212FormatException("Field is missing: " + flag.toString() + ": " + field);
    }

    public static void crc_verification_failed(Enum<?> flag, char[] msg, char[] crc) throws T212FormatException {
        throw new T212FormatException("Crc Verification failed: " + new String(msg) + ": " + new String(crc));
    }

    public static void crc_verification_failed(Enum<?> flag, char[] msg, int crc) throws T212FormatException {
        throw new T212FormatException("Crc Verification failed: " + new String(msg) + ": " + Integer.toHexString(crc));
    }

    public static void segment_exception(SegmentFormatException e) throws T212FormatException {
        throw new T212FormatException("Segment format exception in: " + PacketElement.DATA.toString(),e);
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public T212FormatException withResult(Object result) {
        this.result = result;
        return this;
    }
}
