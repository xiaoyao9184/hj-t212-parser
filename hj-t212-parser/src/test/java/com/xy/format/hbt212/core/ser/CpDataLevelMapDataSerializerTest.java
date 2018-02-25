package com.xy.format.hbt212.core.ser;

import com.xy.format.hbt212.core.T212Generator;
import com.xy.format.hbt212.core.cfger.T212Configurator;
import com.xy.format.hbt212.core.feature.ParserFeature;
import com.xy.format.hbt212.core.feature.VerifyFeature;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.segment.base.cfger.Feature;
import com.xy.format.segment.core.feature.SegmentGeneratorFeature;
import org.junit.Test;

import javax.validation.Validation;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by qyw on 2018/2/25.
 */
public class CpDataLevelMapDataSerializerTest {

    @Test
    public void testDeserialize(){
        String t212 = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";

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

        StringWriter writer = new StringWriter();
        T212Generator generator = new T212Generator(writer);
        CpDataLevelMapDataSerializer serializer = new CpDataLevelMapDataSerializer();

        T212Configurator configurator = new T212Configurator();
        configurator.setVerifyFeature(Feature.collectFeatureDefaults(VerifyFeature.class));
        configurator.setParserFeature(Feature.collectFeatureDefaults(ParserFeature.class));
        configurator.setSegmentGeneratorFeature(Feature.collectFeatureDefaults(SegmentGeneratorFeature.class));
        configurator.setValidator(Validation.buildDefaultValidatorFactory().getValidator());
        configurator.configure(serializer);

        try (T212Generator g = generator) {
            serializer.serialize(g,data);

            t212 = t212.replace(",",";");
            t212 = t212.replace("4980","e981");

            assertEquals(writer.toString(),t212);
        } catch (T212FormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}