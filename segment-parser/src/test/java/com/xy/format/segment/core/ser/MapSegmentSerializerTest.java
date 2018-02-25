package com.xy.format.segment.core.ser;

import com.xy.format.segment.core.SegmentGenerator;
import com.xy.format.segment.exception.SegmentFormatException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/2/25.
 */
public class MapSegmentSerializerTest {

    @Test
    public void testH212(){
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("ST","32");
        map.put("CN","2011");
        map.put("PW","123456");
        map.put("MN","LD130133000015");

        Map<String,String> cp = new LinkedHashMap<>();
        map.put("CP",cp);

        cp.put("DataTime","20160824003817000");
        cp.put("B01-Rtd","36.91");
        cp.put("011-Rtd","231.0");
        cp.put("011-Flag","N");
        cp.put("060-Rtd","1.803");
        cp.put("060-Flag","N");

        String data = "ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&";
        data = data.replace(",",";");

        StringWriter writer = new StringWriter();
        SegmentGenerator generator = new SegmentGenerator(writer);

        MapSegmentSerializer serializer = new MapSegmentSerializer();
        MapValueSegmentSerializer valueSerializer = new MapValueSegmentSerializer(serializer,
                Map.class::cast);
        serializer.setValueDeserializer(valueSerializer);

        try (SegmentGenerator g = generator) {
            serializer.serialize(g,map);

            assertEquals(writer.toString(),data);
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
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("C","c");

        Map<String,Object> b = new LinkedHashMap<>();
        map.put("B",b);

        b.put("B1","b1");

        Map<String,String> a = new LinkedHashMap<>();
        b.put("A",a);

        a.put("A1","a1");
        a.put("A2","a2");
        a.put("A3","a3");

        String data = "C=c;B=&&B1=b1;A=&&A1=a1;A2=a2;A3=a3&&&&";
        StringWriter writer = new StringWriter();
        SegmentGenerator generator = new SegmentGenerator(writer);

        MapSegmentSerializer serializer = new MapSegmentSerializer();
        MapValueSegmentSerializer valueSerializer = new MapValueSegmentSerializer(serializer,
                Map.class::cast);
        serializer.setValueDeserializer(valueSerializer);

        try (SegmentGenerator g = generator) {
            serializer.serialize(g,map);

            assertEquals(writer.toString(),data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SegmentFormatException e) {
            e.printStackTrace();
        }
    }

}