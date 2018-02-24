package com.xy.format.segment.core;

import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/2/24.
 */
public class SegmentGeneratorTest {

    @Test
    public void testWriteObjectValue(){
        String data = "ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&";
        StringWriter writer = new StringWriter();
        SegmentGenerator generator = new SegmentGenerator(writer);

        try {
            generator.initToken();

            //ST=32;CN=2011;PW=123456;MN=LD130133000015;CP
            generator.writeKey("ST");
            generator.writeValue("32");
            generator.writeKey("CN");
            generator.writeValue("2011");
            generator.writeKey("PW");
            generator.writeValue("123456");
            generator.writeKey("MN");
            generator.writeValue("LD130133000015");
            generator.writeKey("CP");

            //&&DataTime=20160824003817000;
            generator.writeObjectValue("DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N");

            assertEquals(writer.toString(),data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testWriteObject(){
        String data = "ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&";
        StringWriter writer = new StringWriter();
        SegmentGenerator generator = new SegmentGenerator(writer);

        try {
            generator.initToken();

            //ST=32;CN=2011;PW=123456;MN=LD130133000015;CP
            generator.writeKey("ST");
            generator.writeValue("32");
            generator.writeKey("CN");
            generator.writeValue("2011");
            generator.writeKey("PW");
            generator.writeValue("123456");
            generator.writeKey("MN");
            generator.writeValue("LD130133000015");
            generator.writeKey("CP");

            //&&DataTime=20160824003817000;
            SegmentGenerator generatorObject = generator.writeObjectStart();

            generatorObject.writeKey("DataTime");
            generatorObject.writeValue("20160824003817000");
            generatorObject.writePathKey("B01");
            generatorObject.writePathKey("Rtd");
            generatorObject.writeValue("36.91");

            generatorObject.writeKey("011");
            generatorObject.writePathKey("Rtd");
            generatorObject.writeValue("231.0");
            generatorObject.writePathKey("Flag");
            generatorObject.writeValue("N");

            generatorObject.writeKey("060");
            generatorObject.writePathKey("Rtd");
            generatorObject.writeValue("1.803");
            generatorObject.writePathKey("Flag");
            generatorObject.writeValue("N");

            generator.writeObjectEnd();

            assertEquals(writer.toString(),data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}