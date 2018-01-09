package com.xy.format.segment.core.deser;

import com.xy.format.segment.core.SegmentParser;
import com.xy.format.segment.exception.FormatException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapSegmentDeserializerTest {

    @Test
    public void testH212(){
        String data = "ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&";
        StringReader reader = new StringReader(data);
        SegmentParser parser = new SegmentParser(reader);

        MapSegmentDeserializer deserializer = new MapSegmentDeserializer();
        try (SegmentParser parser1 = parser) {
            Map<String,Object> map = deserializer.deserialize(parser1);

            assertTrue(map.containsKey("ST"));
            assertEquals(map.get("ST"),"32");
            assertTrue(map.containsKey("CN"));
            assertEquals(map.get("CN"),"2011");
            assertTrue(map.containsKey("PW"));
            assertEquals(map.get("PW"),"123456");
            assertTrue(map.containsKey("MN"));
            assertEquals(map.get("MN"),"LD130133000015");
            assertTrue(map.containsKey("CP"));

            Object b = map.get("CP");
            assertTrue(b instanceof Map);
            map = (Map<String, Object>) map.get("CP");
            assertTrue(map.containsKey("DataTime"));
            assertEquals(map.get("DataTime"),"20160824003817000");
            assertTrue(map.containsKey("B01-Rtd"));
            assertEquals(map.get("B01-Rtd"),"36.91");
            assertTrue(map.containsKey("011-Rtd"));
            assertEquals(map.get("011-Rtd"),"231.0");
            assertTrue(map.containsKey("011-Flag"));
            assertEquals(map.get("011-Flag"),"N");
            assertTrue(map.containsKey("060-Rtd"));
            assertEquals(map.get("060-Rtd"),"1.803");
            assertTrue(map.containsKey("060-Flag"));
            assertEquals(map.get("060-Flag"),"N");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeepObject(){

        /*
          B:{
              B1:b1,
              A:{
                  A1:a1,
                  A2:a2,
                  A3:a3
              }
          }
         */
        String data = "C=c;B=&&B1=b1;A=&&A1=a1;A2=a2;A3=a3&&&&";
        StringReader reader = new StringReader(data);
        SegmentParser parser = new SegmentParser(reader);
        MapSegmentDeserializer deserializer = new MapSegmentDeserializer();
        try(SegmentParser parser1 = parser) {
            Map<String,Object> map = deserializer.deserialize(parser1);

            assertTrue(map.containsKey("C"));
            assertEquals(map.get("C"),"c");
            assertTrue(map.containsKey("B"));
            Object b = map.get("B");
            assertTrue(b instanceof Map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

}