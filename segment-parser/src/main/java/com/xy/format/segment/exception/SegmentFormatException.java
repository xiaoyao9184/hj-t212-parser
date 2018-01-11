package com.xy.format.segment.exception;

/**
 * Created by xiaoyao9184 on 2017/12/19.
 */
public class SegmentFormatException extends Exception {

    public SegmentFormatException(String message) {
        super(message);
    }

    public static void separator_position(char c, Enum<?> mode) throws SegmentFormatException {
        throw new SegmentFormatException("Separator position is wrong: " + c + " cant in Mode: " + mode.name());
    }

    public static void static_data_match(Enum<?> flag, char[] tar, char[] src) throws SegmentFormatException {
        throw new SegmentFormatException("Static data core: " + flag.toString() + ": " + new String(tar) + " -> " + new String(src));
    }

    public static void length_not_match(Enum<?> flag, int tar, int src) throws SegmentFormatException {
        throw new SegmentFormatException("Length does not core: " + flag.toString() + ": " + tar + " -> " + src);
    }

    public static void length_not_range(Enum<?> flag, int src, int min, int max) throws SegmentFormatException {
        throw new SegmentFormatException("Length does not in range: " + flag.toString() + ": " + src + " -> (" + min + "," + max + ")");
    }

    public static void field_is_missing(Enum<?> flag, String field) throws SegmentFormatException {
        throw new SegmentFormatException("Length does not in range: " + flag.toString() + ": " + field);
    }

    public static void crc_verification_failed(Enum<?> flag, char[] msg, char[] crc) throws SegmentFormatException {
        throw new SegmentFormatException("Length does not in range: " + new String(msg) + ": " + new String(crc));
    }

}
