package com.xy.format.hbt212.core.validator.clazz;

import com.xy.format.hbt212.model.verify.T212Map;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/1/12.
 */
public class T212MapCValidatorTest {

    public Map<String,String> map;

    @Before
    public void init(){
        map = new HashMap<>();
        map.put("ST","12");
        map.put("CN","1234-");
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void test(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        T212Map t212Map = T212Map.createDataLevel(map);

        Set<ConstraintViolation<T212Map>> e1 = validator.validate(t212Map,
                Default.class);
        assertEquals(e1.size(),1);
    }
}