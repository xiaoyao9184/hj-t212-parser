package com.xy.format.hbt212.core.deser;

import com.xy.format.hbt212.core.T212Parser;
import com.xy.format.hbt212.core.cfger.T212Configurator;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.Pack;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/1/11.
 */
public class PackLevelDeserializerTest {

    @Test
    public void test(){
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=NValidator;060-Rtd=1.803,060-Flag=NValidator&&4980\n";
        StringReader reader = new StringReader(data);
        T212Parser t212Parser = new T212Parser(reader);
        T212Configurator configurator = new T212Configurator();
        PackLevelDeserializer deserializer = new PackLevelDeserializer();
        deserializer.configured(configurator);

        try (T212Parser parser = t212Parser) {
            Pack pack = deserializer.deserialize(parser);

            assertNotNull(pack);
        } catch (T212FormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}