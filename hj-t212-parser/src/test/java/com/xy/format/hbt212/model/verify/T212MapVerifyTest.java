package com.xy.format.hbt212.model.verify;

import com.xy.format.hbt212.model.verify.groups.T212MapLevelGroup;
import com.xy.format.hbt212.model.verify.groups.VersionGroup;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Created by xiaoyao9184 on 2018/1/11.
 */
public class T212MapVerifyTest {

    public T212Map<String,String> dataLevel;
    public T212Map<String,Object> cpDataLevel;

    @Before
    public void init(){
        dataLevel = T212Map.createDataLevel(new HashMap<>());
        dataLevel.put("QN","20180101123010123");
        dataLevel.put("ST","12");
        dataLevel.put("CN","1234");
        dataLevel.put("PW","123456");
        dataLevel.put("MN","12345678901234");
        dataLevel.put("Flag","0");
        dataLevel.put("PNUM","0");
        dataLevel.put("PNO","0");
        dataLevel.put("CP",String.join("", Collections.nCopies(950,"#")));

        cpDataLevel = T212Map.createCpDataLevel(new HashMap<>());
        cpDataLevel.putAll(dataLevel);
        cpDataLevel.remove("CP");
        Map<String,String> cp = new HashMap<>();
        cpDataLevel.put("CP",cp);
        cp.put("SystemTime","20180101123010123");
        cp.put("QN","20180101123010123");
        cp.put("QnRtn","123");
        cp.put("ExeRtn","123");
        cp.put("RtdInterval","30");
        cp.put("MinInterval","99");
        cp.put("RestartTime","20180101123010123");
        cp.put("AlarmTime","20180101123010");
        cp.put("AlarmType","1");
        cp.put("ReportTarget","12345678901234567890");
        cp.put("PolId","123456");
        cp.put("BeginTime","20180101123010123");
        cp.put("EndTime","20180101123010123");
        cp.put("ReportTime","1234");
        cp.put("DayStdValue","12345678901234");
        cp.put("NightStdValue","20180101123010123");
        cp.put("Flag","0");
        cp.put("PNO","1");
        cp.put("PW","123456");
        cp.put("NewPW","123456");
        cp.put("OverTime","99999");
        cp.put("ReCount","99");
        cp.put("WarnTime","99999");
        cp.put("CTime","99");
        cp.put("VaseNo","99");
        cp.put("CstartTime","123010");
        cp.put("Stime","120");
        cp.put("InfoId","123456");

        cp.put("B01-Rtd","36.91");
        cp.put("011-Rtd","231.0");
        cp.put("011-Flag","N");
        cp.put("060-Rtd","1.803");
        cp.put("060-Flag","N");

    }

    @Test
    public void testDataLevel(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T212Map>> e2005 = validator.validate(dataLevel,Default.class,
                T212MapLevelGroup.DataLevel.class, VersionGroup.V2005.class);
        assertTrue(e2005.isEmpty());
        Set<ConstraintViolation<T212Map>> e2017 = validator.validate(dataLevel,Default.class,
                T212MapLevelGroup.DataLevel.class, VersionGroup.V2017.class);
        assertTrue(e2017.isEmpty());
    }

    @Test
    public void testDataLevelFalse(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Map m = dataLevel.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        kv -> kv.getValue() + "---------------"
                ));
        T212Map t212Map = T212Map.createDataLevel(m);

        Set<ConstraintViolation<T212Map>> e2005 = validator.validate(t212Map,Default.class,
                VersionGroup.V2005.class);
        assertTrue(e2005.size() > 0);
        Set<ConstraintViolation<T212Map>> e2017 = validator.validate(t212Map,Default.class,
                VersionGroup.V2017.class);
        assertTrue(e2017.size() > 0);
    }

    @Test
    public void testCPDataLevel(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T212Map>> e2005 = validator.validate(cpDataLevel,Default.class,
                T212MapLevelGroup.CpDataLevel.class, VersionGroup.V2005.class);
        assertTrue(e2005.isEmpty());
        Set<ConstraintViolation<T212Map>> e2017 = validator.validate(cpDataLevel,Default.class,
                T212MapLevelGroup.CpDataLevel.class, VersionGroup.V2017.class);
        assertTrue(e2017.isEmpty());
    }

    @Test
    public void testCPDataLevelFalse(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Map data = cpDataLevel.entrySet()
                .stream()
                .filter(kv -> !kv.getKey().equals("CP"))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        kv -> kv.getValue() + "---------------"
                ));
        Map<String,String> cp1 = (Map) cpDataLevel.get("CP");
        Map cp = cp1.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        kv -> kv.getValue() + "---------------"
                ));
        data.put("CP",cp);

        T212Map t212Map = T212Map.createCpDataLevel(data);

        Set<ConstraintViolation<T212Map>> e2005 = validator.validate(t212Map,Default.class,
                VersionGroup.V2005.class);
        assertTrue(e2005.size() > 0);
        Set<ConstraintViolation<T212Map>> e2017 = validator.validate(t212Map,Default.class,
                VersionGroup.V2017.class);
        assertTrue(e2017.size() > 0);
    }

}