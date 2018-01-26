package com.xy.format.hbt212.model.mixin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.format.hbt212.model.CpData;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/1/25.
 */
public class CpDataDeserializationMixinTest {

    @Test
    public void testMixinWithAlias(){
        Map<String,String> map = new HashMap<>();
        map.put("cTime","1");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(CpData.class,CpDataDeserializationMixin.class);

        CpData cp = objectMapper.convertValue(map, CpData.class);
        assertEquals(cp.getcTime(),1);

        map.clear();
        cp = null;
        map.put("Ctime","2");
        cp = objectMapper.convertValue(map, CpData.class);
        assertEquals(cp.getcTime(),2);

        map.clear();
        cp = null;
        map.put("Ctime","2");
        map.put("cTime","1");
        cp = objectMapper.convertValue(map, CpData.class);
        assertEquals(cp.getcTime(),1);

    }

    @Test
    public void testIgnore(){
        Map<String,String> map = new HashMap<>();
        map.put("Flag","N");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.addMixIn(CpData.class,CpDataDeserializationMixin.class);

        CpData cp = objectMapper.convertValue(map, CpData.class);
        assertNull(cp.getDataFlag());
    }

}