package com.xy.format.hbh212.exception;

/**
 * Created by xiaoyao9184 on 2017/12/19.
 */
public class FormatException extends Exception {

    public FormatException(String message) {
        super(message);
    }

    public static void separator_position(char c, Enum<?> mode) throws FormatException {
        throw new FormatException("Separator position is wrong: " + c + " cant in Mode: " + mode.name());
    }

    public static void static_data_match(Enum<?> flag, char[] tar, char[] src) throws FormatException {
        throw new FormatException("Static data match: " + flag.toString() + ": " + new String(tar) + " -> " + new String(src));
    }

    public static void length_not_match(Enum<?> flag, int tar, int src) throws FormatException {
        throw new FormatException("Length does not match: " + flag.toString() + ": " + tar + " -> " + src);
    }

    public static void length_not_range(Enum<?> flag, int src, int min, int max) throws FormatException {
        throw new FormatException("Length does not in range: " + src + " -> (" + min + "," + max + ")");
    }

}
