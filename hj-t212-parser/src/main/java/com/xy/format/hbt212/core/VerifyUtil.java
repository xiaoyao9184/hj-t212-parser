package com.xy.format.hbt212.core;

import com.xy.format.hbt212.exception.T212FormatException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class VerifyUtil {

    public static void verifyChar(char[] tar, char[] src, Enum<?> e) throws T212FormatException {
        Objects.requireNonNull(tar);
        Objects.requireNonNull(src);
        if(!Arrays.equals(src, tar)){
            T212FormatException.static_data_match(e,tar,src);
        }
    }

    public static void verifyChar(byte[] tar, char[] src, Enum<?> e) throws T212FormatException {
        char[] c = new String(tar).toCharArray();
        verifyChar(src,c,e);
    }

    public static void verifyLen(int count, int length, Enum<?> e) throws T212FormatException {
        if(count != length){
            T212FormatException.length_not_match(e,count,length);
        }
    }

    public static void verifyLen(String str, int min, int max, Enum<?> e) throws T212FormatException {
        if(str == null){
            return;
        }
        int len = str.length();

        if(len >= min  && len <= max){
        }else{
            T212FormatException.length_not_range(e,len,min,max);
        }
    }

    public static void verifyLen(String str, int length, Enum<?> e) throws T212FormatException {
        if(str == null){
            return;
        }

        verifyLen(str.length(),length,e);
    }

    public static void verifyRange(int src, int min, int max, Enum<?> e) throws T212FormatException {
        if(src >= min  && src <= max){
        }else{
            T212FormatException.length_not_range(e,src,min,max);
        }
    }

    public static String verifyRange(String str, int min, int max, Enum<?> e) throws T212FormatException {
        int src = 0;
        if(str != null){
            src = str.length();
        }

        if(src >= min  && src <= max){
        }else{
            T212FormatException.length_not_range(e,src,min,max);
        }
        return str;
    }

    public static void verifyCrc(char[] msg, char[] crc, Enum<?> e) throws T212FormatException {
        int crc16 = T212Parser.crc16Checkout(msg,msg.length);
        int crcSrc = Integer.parseInt(new String(crc),16);

        if(crc16 != crcSrc){
            T212FormatException.crc_verification_failed(e,msg,crc);
        }
    }

    public static void verifyHave(Map<String,?> object, Enum<?> e) throws T212FormatException {
        if(!object.containsKey(e.name())){
            T212FormatException.field_is_missing(e,e.name());
        }
    }

}
