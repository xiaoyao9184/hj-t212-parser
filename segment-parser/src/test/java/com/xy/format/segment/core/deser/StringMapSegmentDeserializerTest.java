package com.xy.format.segment.core.deser;

import com.xy.format.segment.core.SegmentParser;
import com.xy.format.segment.exception.SegmentFormatException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static org.junit.Assert.*;

public class StringMapSegmentDeserializerTest {

    @Test
    public void testT212(){
        String data = "ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&";
        StringReader reader = new StringReader(data);
        SegmentParser parser = new SegmentParser(reader);

        StringMapSegmentDeserializer deserializer = new StringMapSegmentDeserializer();
        try(SegmentParser parser1 = parser) {
            Map<String,String> map = deserializer.deserialize(parser1);

            assertTrue(map.containsKey("ST"));
            assertEquals(map.get("ST"),"32");
            assertTrue(map.containsKey("CN"));
            assertEquals(map.get("CN"),"2011");
            assertTrue(map.containsKey("PW"));
            assertEquals(map.get("PW"),"123456");
            assertTrue(map.containsKey("MN"));
            assertEquals(map.get("MN"),"LD130133000015");
            assertTrue(map.containsKey("CP"));
            assertEquals(map.get("CP"),"DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SegmentFormatException e) {
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
        StringMapSegmentDeserializer deserializer = new StringMapSegmentDeserializer();
        try(SegmentParser parser1 = parser) {
            Map<String,String> map = deserializer.deserialize(parser1);

            assertTrue(map.containsKey("B"));
            assertEquals(map.get("B"),"B1=b1;A=&&A1=a1;A2=a2;A3=a3&&");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SegmentFormatException e) {
            e.printStackTrace();
        }
    }

}