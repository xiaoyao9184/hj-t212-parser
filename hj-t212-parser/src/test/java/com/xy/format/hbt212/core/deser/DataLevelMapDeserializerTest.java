package com.xy.format.hbt212.core.deser;

import com.xy.format.hbt212.core.T212Parser;
import com.xy.format.hbt212.core.cfger.T212Configurator;
import com.xy.format.hbt212.core.feature.ParserFeature;
import com.xy.format.hbt212.core.feature.VerifyFeature;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.Pack;
import com.xy.format.segment.base.cfger.Feature;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static com.xy.format.hbt212.core.feature.VerifyFeature.ALLOW_MISSING_FIELD;
import static com.xy.format.hbt212.core.feature.VerifyFeature.ALLOW_VALUE_NOT_IN_RANGE;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/1/11.
 */
public class DataLevelMapDeserializerTest {

    @Test
    public void test(){
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        StringReader reader = new StringReader(data);
        T212Parser t212Parser = new T212Parser(reader);
        T212Configurator configurator = new T212Configurator();
        int vFeature = Feature.collectFeatureDefaults(VerifyFeature.class);
        vFeature = vFeature | ALLOW_MISSING_FIELD.getMask();
        vFeature = vFeature | ALLOW_VALUE_NOT_IN_RANGE.getMask();
        configurator.setVerifyFeature(vFeature);
        configurator.setParserFeature(Feature.collectFeatureDefaults(ParserFeature.class));
        DataLevelMapDeserializer deserializer = new DataLevelMapDeserializer();
        deserializer.configured(configurator);

        try (T212Parser parser = t212Parser) {
            Map<String, String> map = deserializer.deserialize(parser);

            assertNotNull(map);
            assertEquals(map.get("ST"),"32");
            assertEquals(map.get("CN"),"2011");
            assertEquals(map.get("PW"),"123456");
            assertEquals(map.get("MN"),"LD130133000015");
            assertEquals(map.get("CP"),"DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N");
        } catch (T212FormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}