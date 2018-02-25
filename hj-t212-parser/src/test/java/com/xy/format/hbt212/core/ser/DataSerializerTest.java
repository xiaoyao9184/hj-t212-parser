package com.xy.format.hbt212.core.ser;

import com.xy.format.hbt212.core.T212Generator;
import com.xy.format.hbt212.core.cfger.T212Configurator;
import com.xy.format.hbt212.core.feature.ParserFeature;
import com.xy.format.hbt212.core.feature.VerifyFeature;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.CpData;
import com.xy.format.hbt212.model.Data;
import com.xy.format.hbt212.model.Pollution;
import com.xy.format.segment.base.cfger.Feature;
import com.xy.format.segment.core.feature.SegmentGeneratorFeature;
import org.junit.Test;

import javax.validation.Validation;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/2/25.
 */
public class DataSerializerTest {

    @Test
    public void test(){
        String t212 = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";

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


        StringWriter writer = new StringWriter();
        T212Generator generator = new T212Generator(writer);
        DataSerializer serializer = new DataSerializer();

        T212Configurator configurator = new T212Configurator();
        configurator.setVerifyFeature(Feature.collectFeatureDefaults(VerifyFeature.class));
        configurator.setParserFeature(Feature.collectFeatureDefaults(ParserFeature.class));
        configurator.setSegmentGeneratorFeature(Feature.collectFeatureDefaults(SegmentGeneratorFeature.class));
        configurator.setValidator(Validation.buildDefaultValidatorFactory().getValidator());

        generator.configured(configurator);
        serializer.configured(configurator);

        try (T212Generator g = generator) {
            serializer.serialize(g,data);

            assertEquals(writer.toString(),t212);
        } catch (T212FormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}