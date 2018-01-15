package com.xy.format.hbt212.core.validator.field;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/1/12.
 */
public class NValidatorTest {

    @Test
    public void test(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        TestBean bean = new TestBean();
        bean.n1 = "1";
        bean.n2_2 = "120.345";

        Set<ConstraintViolation<TestBean>> e1 = validator.validate(bean,
                Default.class);
        assertEquals(e1.size(),1);
    }

    public class TestBean {
        @N(integer = 1)
        public String n1;
        @N(integer = 2,fraction = 2)
        public String n2_2;
    }
}