package com.xy.format.hbh212.core;

import com.xy.format.hbh212.exception.FormatException;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class Verify {

    public static void verifyChar(char[] tar, char[] src, Enum<?> e) throws FormatException {
        Objects.requireNonNull(tar);
        Objects.requireNonNull(src);
        if(!Arrays.equals(src, tar)){
            FormatException.static_data_match(e,tar,src);
        }
    }

    public static void verifyChar(byte[] tar, char[] src, Enum<?> e) throws FormatException {
        char[] c = new String(tar).toCharArray();
        verifyChar(src,c,e);
    }

    public static void verifyLen(int count, int length, Enum<?> e) throws FormatException {
        if(count != length){
            FormatException.length_not_match(e,count,length);
        }
    }

    public static void verifyLen(String str, int min, int max, Enum<?> e) throws FormatException {
        if(str == null){
            return;
        }
        int len = str.length();

        if(len >= min  && len <= max){
        }else{
            FormatException.length_not_range(e,len,min,max);
        }
    }

    public static void verifyLen(String str, int length, Enum<?> e) throws FormatException {
        if(str == null){
            return;
        }

        verifyLen(str.length(),length,e);
    }

    public static void verifyRange(int src, int min, int max, Enum<?> e) throws FormatException {
        if(src >= min  && src <= max){
        }else{
            FormatException.length_not_range(e,src,min,max);
        }
    }

    public static String verifyRange(String str, int min, int max, Enum<?> e) throws FormatException {
        int src = 0;
        if(str != null){
            src = str.length();
        }

        if(src >= min  && src <= max){
        }else{
            FormatException.length_not_range(e,src,min,max);
        }
        return str;
    }

    public static void verifyCrc(int count, int length, Enum<?> e) throws FormatException {
        if(count != length){
            FormatException.length_not_match(e,count,length);
        }
    }


    public enum DataType {
        C4,
        N5,
        N14_2;
    }

}
