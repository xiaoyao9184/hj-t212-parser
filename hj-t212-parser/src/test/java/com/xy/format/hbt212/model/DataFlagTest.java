package com.xy.format.hbt212.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/1/3.
 */
public class DataFlagTest {

    @Test
    public void getBit() throws Exception {
        assertEquals(DataFlag.A.getBit(),1);
        assertEquals(DataFlag.D.getBit(),2);
        assertEquals(DataFlag.V0.getBit(),4);
        assertEquals(DataFlag.V1.getBit(),8);
        assertEquals(DataFlag.V2.getBit(),16);
        assertEquals(DataFlag.V3.getBit(),32);
        assertEquals(DataFlag.V4.getBit(),64);
        assertEquals(DataFlag.V5.getBit(),128);
    }

    @Test
    public void isMarked() throws Exception {
        //示例：Flag=7 表示标准版本为本次修订版本号，数据段需要拆分并且命令需要应答
        assertTrue(DataFlag.A.isMarked(7));
        assertTrue(DataFlag.D.isMarked(7));
        assertTrue(DataFlag.V0.isMarked(7));
        assertFalse(DataFlag.V1.isMarked(7));
        assertFalse(DataFlag.V2.isMarked(7));
        assertFalse(DataFlag.V3.isMarked(7));
        assertFalse(DataFlag.V4.isMarked(7));
        assertFalse(DataFlag.V5.isMarked(7));
    }

}