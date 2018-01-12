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
public class ValidDateValidatorTest {

    @Test
    public void test(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        TestBean bean = new TestBean();
        bean.yyyy = "2018";
        bean.hh = "2530";

        Set<ConstraintViolation<TestBean>> e1 = validator.validate(bean,
                Default.class);
        assertEquals(e1.size(),1);
    }

    public class TestBean {
        @ValidDate(format = "YYYY")
        public String yyyy;
        @ValidDate(format = "HH")
        public String hh;
    }
}