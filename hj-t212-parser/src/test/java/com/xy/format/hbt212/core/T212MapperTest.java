package com.xy.format.hbt212.core;

import com.xy.format.hbt212.model.CpData;
import com.xy.format.hbt212.model.Data;
import com.xy.format.hbt212.model.Pollution;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
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
                .enableDefaultVerifyFeatures();
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
        String h212 = "##0136ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=" +
                "&&DataTime=20160824003817;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&" +
                "4980\r\n";
        T212Mapper mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures();
        try {
            Map<String,Object> data = mapper.readDeepMap(h212);

            assertEquals(data.get("ST"),"32");
            assertEquals(data.get("CN"),"2011");
            assertEquals(data.get("PW"),"123456");
            assertEquals(data.get("MN"),"LD130133000015");

            Map<String,String> cp = (Map<String, String>) data.get("CP");
            assertEquals(cp.get("DataTime"),"20160824003817");
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
        String h212 = "##0136ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=" +
                "&&DataTime=20160824003817;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&" +
                "4980\r\n";
        T212Mapper mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures();
        try {
            Data data = mapper.readData(h212);

            assertEquals(data.getSt(),"32");
            assertEquals(data.getCn(),"2011");
            assertEquals(data.getPw(),"123456");
            assertEquals(data.getMn(),"LD130133000015");

            CpData cp = data.getCp();
            assertEquals(cp.getDataTime(),"20160824003817");
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



    @Test
    public void writeMap() {
        String t212 = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=" +
                "&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&" +
                "4980\r\n";
        t212 = t212.replace(",",";");
        t212 = t212.replace("4980","e981");

        Map<String,Object> data = new LinkedHashMap<>();
        data.put("ST","32");
        data.put("CN","2011");
        data.put("PW","123456");
        data.put("MN","LD130133000015");

        Map<String,Object> cp = new LinkedHashMap<>();
        data.put("CP",cp);
        cp.put("DataTime","20160824003817000");
        cp.put("B01-Rtd","36.91");
        cp.put("011-Rtd","231.0");
        cp.put("011-Flag","N");
        cp.put("060-Rtd","1.803");
        cp.put("060-Flag","N");

        T212Mapper mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures();
        try {
            String result = mapper.writeMapAsString(data);

            assertEquals(result,t212);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void writeData() {
        String t212 = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=" +
                "&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&" +
                "4980\r\n";

        Data data = new Data();
        data.setSt("32");
        data.setCn("2011");
        data.setPw("123456");
        data.setMn("LD130133000015");

        CpData cp = new CpData();
        data.setCp(cp);
        cp.setDataTime("20160824003817000");

        Map<String,Pollution> pollutionMap = new LinkedHashMap<>();
        cp.setPollution(pollutionMap);

        Pollution pB01 = new Pollution();
        pollutionMap.put("B01",pB01);
        pB01.setRtd(new BigDecimal("36.91"));

        Pollution p011 =  new Pollution();
        pollutionMap.put("011",p011);
        p011.setRtd(new BigDecimal("231.0"));
        p011.setFlag("N");

        Pollution p060 = new Pollution();
        pollutionMap.put("060",p060);
        p060.setRtd(new BigDecimal("1.803"));
        p060.setFlag("N");

        T212Mapper mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures();
        try {
            String result = mapper.writeDataAsString(data);

            assertEquals(result,t212);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

}