package com.xy.format.hbt212;

import com.xy.format.hbt212.coding.GBT16706;
import org.junit.Test;

import javax.activation.UnsupportedDataTypeException;

import java.lang.annotation.ElementType;

import static org.junit.Assert.*;

public class H212TranslatorTest {

    @Test
    public void testTranslation() {
        try {
            String mean = H212Translator.I.translation(GBT16706.class,"21");
            assertEquals(GBT16706._21.mean(),mean);
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
            H212Translator.I.translation(GBT16706.class,"21-not");
        } catch (UnsupportedDataTypeException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            assert true;
        }
    }

}
