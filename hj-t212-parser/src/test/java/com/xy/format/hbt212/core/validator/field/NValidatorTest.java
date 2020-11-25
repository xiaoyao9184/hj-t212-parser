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
        @N(integer = 3)
        public String n3;
        @N(integer = 1)
        public String optional;
    }


    @Test
    public void testNumberLength(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        TestBean bean = new TestBean();
        bean.n3 = "1";

        Set<ConstraintViolation<TestBean>> e1 = validator.validate(bean,
                Default.class);
        assertEquals(e1.size(),0);

        bean.n3 = "12";
        Set<ConstraintViolation<TestBean>> e2 = validator.validate(bean,
                Default.class);
        assertEquals(e2.size(),0);

        bean.n3 = "123";
        Set<ConstraintViolation<TestBean>> e3 = validator.validate(bean,
                Default.class);
        assertEquals(e3.size(),0);

        bean.n3 = "1234";
        Set<ConstraintViolation<TestBean>> e4 = validator.validate(bean,
                Default.class);
        assertEquals(e4.size(),1);
    }

    @Test
    public void testOptional(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        TestBean bean = new TestBean();
        bean.optional = "12";

        Set<ConstraintViolation<TestBean>> e1 = validator.validate(bean,
                Default.class);
        assertEquals(e1.size(),1);

        bean.optional = null;
        Set<ConstraintViolation<TestBean>> e2 = validator.validate(bean,
                Default.class);
        assertEquals(e2.size(),0);

    }

}