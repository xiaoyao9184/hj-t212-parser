package com.xy.format.hbt212.model.mixin;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.format.hbt212.model.CpData;
import com.xy.format.hbt212.model.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.junit.Assert.assertNull;

/**
 * Created by xiaoyao9184 on 2018/1/25.
 */
public class DataDeserializationMixinTest {

    @Test
    public void testIgnore(){
        Map<String,String> map = new HashMap<>();
        map.put("ST","31");
        map.put("Flag","N");
        map.put("CP","&&k=v&&");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.addMixIn(Data.class,DataDeserializationMixin.class);

        Data data = objectMapper.convertValue(map, Data.class);
        assertNull(data.getDataFlag());
    }

}