package com.xy.format.hbt212.core.deser;

import com.xy.format.hbt212.core.T212Parser;
import com.xy.format.hbt212.core.cfger.T212Configurator;
import com.xy.format.hbt212.core.feature.VerifyFeature;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.Pack;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by xiaoyao9184 on 2018/1/11.
 */
public class PackLevelDeserializerTest {

    @Test
    public void test(){
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\n";
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

    @Test
    public void testFeature_DATA_LEN_RANGE() {
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        String a = String.join("", Collections.nCopies(1024 - 139 + 1,"#"));
        data = data.replace("060-Flag=N&&4980","060-Flag=N" + a + "&&4980");
        data = data.replace("##0139","##1025");

        T212Configurator configurator = new T212Configurator();
        StringReader reader = new StringReader(data);
        T212Parser parser = new T212Parser(reader);
        PackLevelDeserializer deserializer = new PackLevelDeserializer();
        deserializer.setParserConfigurator(configurator);
        deserializer.setVerifyFeature(VerifyFeature.DATA_LEN_RANGE.getMask());
        try (T212Parser p = parser) {
            deserializer.deserialize(p);
        } catch (T212FormatException | IOException e) {
            assertTrue(e.getMessage().contains("Length"));
        }

        reader = new StringReader(data);
        parser = new T212Parser(reader);
        deserializer = new PackLevelDeserializer();
        deserializer.setParserConfigurator(configurator);
        try (T212Parser p = parser) {
            Pack pack = deserializer.deserialize(p);
            assertNotNull(pack);
        } catch (T212FormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFeature_DATA_CRC() {
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4981\r\n";
        T212Configurator configurator = new T212Configurator();
        StringReader reader = new StringReader(data);
        T212Parser parser = new T212Parser(reader);
        PackLevelDeserializer deserializer = new PackLevelDeserializer();
        deserializer.configured(configurator);
        deserializer.setVerifyFeature(VerifyFeature.DATA_CRC.getMask());
        try (T212Parser p = parser) {
            deserializer.deserialize(p);
        } catch (T212FormatException | IOException e) {
            assertTrue(e.getMessage().contains("Crc"));
        }

        reader = new StringReader(data);
        parser = new T212Parser(reader);
        deserializer = new PackLevelDeserializer();
        deserializer.configured(configurator);
        try (T212Parser p = parser) {
            Pack pack = deserializer.deserialize(p);
            assertNotNull(pack);
        } catch (T212FormatException | IOException e) {
            e.printStackTrace();
        }
    }

}