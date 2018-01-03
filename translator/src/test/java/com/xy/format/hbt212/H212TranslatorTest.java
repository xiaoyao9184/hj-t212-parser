package com.xy.format.hbt212;

import com.xy.format.hbt212.coding.System;
import org.junit.Test;

import javax.activation.UnsupportedDataTypeException;

import java.lang.annotation.ElementType;

import static org.junit.Assert.*;

public class H212TranslatorTest {

    @Test
    public void testTranslation() {
        try {
            String mean = H212Translator.I.translation(System.class,"21");
            assertEquals(System._21.mean(),mean);
        } catch (UnsupportedDataTypeException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTranslationFailure() {
        try {
            H212Translator.I.translation(ElementType.class,"21");
        } catch (UnsupportedDataTypeException e) {
            assert true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            H212Translator.I.translation(System.class,"21-not");
        } catch (UnsupportedDataTypeException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            assert true;
        }
    }

    @Test
    public void testExpand() {
        try {
            H212Translator.I.expand(System.class,"00","扩展",null);
            String mean = H212Translator.I.translation(System.class,"00");
            assertEquals("扩展",mean);
        } catch (UnsupportedDataTypeException e) {
            assert true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
