package com.xy.format.segment.core.ser;

import com.xy.format.segment.core.SegmentGenerator;
import com.xy.format.segment.exception.SegmentFormatException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/2/24.
 */
public class StringMapSegmentSerializerTest {

    @Test
    public void testT212(){
        String data = "ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&";
        Map<String,String> map = new LinkedHashMap<>();
        map.put("ST","32");
        map.put("CN","2011");
        map.put("PW","123456");
        map.put("MN","LD130133000015");
        map.put("CP","&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&");

        StringWriter writer = new StringWriter();
        SegmentGenerator generator = new SegmentGenerator(writer);

        StringMapSegmentSerializer serializer = new StringMapSegmentSerializer();
        try(SegmentGenerator g = generator) {
            serializer.serialize(g,map);

            assertEquals(writer.toString(),data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SegmentFormatException e) {
            e.printStackTrace();
        }
    }

}