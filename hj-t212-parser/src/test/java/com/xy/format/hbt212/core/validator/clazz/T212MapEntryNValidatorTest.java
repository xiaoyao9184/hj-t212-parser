package com.xy.format.hbt212.core.validator.clazz;

import com.xy.format.hbt212.model.verify.groups.TypeGroup;
import com.xy.format.hbt212.model.verify.T212MapEntry;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/1/11.
 */
public class T212MapEntryNValidatorTest {

    @Test
    public void test(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T212MapEntry>> e1 = validator.validate(T212MapEntry.of("N1","2"),TypeGroup.N1.class);
        assertTrue(e1.isEmpty());
        Set<ConstraintViolation<T212MapEntry>> e2 = validator.validate(T212MapEntry.of("N2","23"),TypeGroup.N2.class);
        assertTrue(e2.isEmpty());
        Set<ConstraintViolation<T212MapEntry>> e3 = validator.validate(T212MapEntry.of("N2.2","23.1"),TypeGroup.N2_2.class);
        assertTrue(e3.isEmpty());
        Set<ConstraintViolation<T212MapEntry>> e4 = validator.validate(T212MapEntry.of("N14","12345678901234"),TypeGroup.N14.class);
        assertTrue(e4.isEmpty());
    }
}