package com.xy.format.segment.core;

import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2018/1/4.
 */
public enum SegmentToken {

    NOT_AVAILABLE(-1),
    NULL_VALUE(),

    END_KEY('='),
    END_PART_KEY('-'),

    END_ENTRY(';'),
    END_SUB_ENTRY(','),

    START_OBJECT_VALUE('&','&'),
    END_OBJECT_VALUE('&','&')
    ;



    private char[] tokens;

    SegmentToken(char... tokens){
        this.tokens = tokens;
    }

    SegmentToken(int token){
        this.tokens = new char[] {(char) token};
    }


    public boolean isSame(char... tokens){
        return Arrays.equals(this.tokens,tokens);
    }

    public boolean isStart(char token){
        return this.tokens[0] == token;
    }

    public char start(){
        return tokens[0];
    }
}
