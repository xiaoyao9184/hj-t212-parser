package com.xy.format.hbt212.core;

import com.xy.format.hbt212.exception.FormatException;
import com.xy.format.hbt212.model.Data;
import com.xy.format.hbt212.model.DataPollution;
import com.xy.format.hbt212.model.DataPollutionFlag;
import com.xy.format.hbt212.model.Segment;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/12/19.
 */
public class DataConverterTest {

    @Test
    public void testConvert(){
        String data = "ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=0&&";

        SegmentConverter segmentConverter = new SegmentConverter();
        DataConverter dataConverter = new DataConverter();
        try {
            Segment s = segmentConverter.convert(data.toCharArray());
            Map<String,Object> map = dataConverter.convertToMap(s);

            assertEquals(map.get("DataTime"),"20160824003817000");
            Map<String,Object> map1 = (Map<String, Object>) map.get("B01");
            assertEquals(map1.get("Rtd"),"36.91");
            Map<String,Object> map2 = (Map<String, Object>) map.get("011");
            assertEquals(map2.get("Rtd"),"231.0");
            assertEquals(map2.get("Flag"),"N");
            Map<String,Object> map3 = (Map<String, Object>) map.get("060");
            assertEquals(map3.get("Rtd"),"1.803");
//            assertEquals(map3.get("Flag"),"N");

            Data data1 = dataConverter.convert(s);
            assertEquals(data1.getDataTime(),"20160824003817000");
            assertEquals(data1.getPw(),"123456");
            assertEquals(data1.getPollution().size(),3);

            DataPollution dp1 = data1.getPollution().get("B01");
            assertEquals(dp1.getRtd(), BigDecimal.valueOf(36.91));
            DataPollution dp2 = data1.getPollution().get("011");
            assertEquals(dp2.getRtd(),BigDecimal.valueOf(231.0));
            assertEquals(dp2.getFlag(), DataPollutionFlag.N.toString());
            DataPollution dp3 = data1.getPollution().get("060");
            assertEquals(dp3.getRtd(),BigDecimal.valueOf(1.803));
            assertEquals(dp3.getFlag(), DataPollutionFlag._0.toString());

        } catch (IOException | FormatException e) {
            e.printStackTrace();
        }

    }

}