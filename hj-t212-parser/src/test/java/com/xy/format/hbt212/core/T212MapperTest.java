package com.xy.format.hbt212.core;

import com.xy.format.hbt212.core.feature.VerifyFeature;
import com.xy.format.hbt212.model.CpData;
import com.xy.format.hbt212.model.Data;
import com.xy.format.hbt212.model.Pollution;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/1/12.
 */
public class T212MapperTest {

    @Test
    public void readMap() {
        String h212 = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        T212Mapper mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures()
                .disable(VerifyFeature.USE_VERIFICATION);
        try {
            Map<String,String> data = mapper.readMap(h212);

            assertEquals(data.get("ST"),"32");
            assertEquals(data.get("CN"),"2011");
            assertEquals(data.get("PW"),"123456");
            assertEquals(data.get("MN"),"LD130133000015");
            assertEquals(data.get("CP"),"DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void readDeepMap() {
        String h212 = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        T212Mapper mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures()
                .disable(VerifyFeature.USE_VERIFICATION);
        try {
            Map<String,Object> data = mapper.readDeepMap(h212);

            assertEquals(data.get("ST"),"32");
            assertEquals(data.get("CN"),"2011");
            assertEquals(data.get("PW"),"123456");
            assertEquals(data.get("MN"),"LD130133000015");

            Map<String,String> cp = (Map<String, String>) data.get("CP");
            assertEquals(cp.get("DataTime"),"20160824003817000");
            assertEquals(cp.get("B01-Rtd"),"36.91");
            assertEquals(cp.get("011-Rtd"),"231.0");
            assertEquals(cp.get("011-Flag"),"N");
            assertEquals(cp.get("060-Rtd"),"1.803");
            assertEquals(cp.get("060-Flag"),"N");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void readData() {
        String h212 = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        T212Mapper mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures()
                .disable(VerifyFeature.USE_VERIFICATION);
//        mapper.objectMapper()
//                .configure(FAIL_ON_UNKNOWN_PROPERTIES,false);
        try {
            Data data = mapper.readData(h212);

            assertEquals(data.getSt(),"32");
            assertEquals(data.getCn(),"2011");
            assertEquals(data.getPw(),"123456");
            assertEquals(data.getMn(),"LD130133000015");

            CpData cp = data.getCp();
            assertEquals(cp.getDataTime(),"20160824003817000");
            Map<String,Pollution> pollutionMap = cp.getPollution();

            Pollution pB01 = pollutionMap.get("B01");
            assertEquals(pB01.getRtd(),new BigDecimal("36.91"));

            Pollution p011 = pollutionMap.get("011");
            assertEquals(p011.getRtd(),new BigDecimal("231.0"));
            assertEquals(p011.getFlag(),"N");

            Pollution p060 = pollutionMap.get("060");
            assertEquals(p060.getRtd(),new BigDecimal("1.803"));
            assertEquals(p060.getFlag(),"N");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

}