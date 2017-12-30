package com.xy.format.hbt212.core;

import com.xy.format.hbt212.exception.FormatException;
import com.xy.format.hbt212.model.Segment;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/12/19.
 */
public class SegmentConverterTest {

    @Test
    public void testConvert(){
        String data = "ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-SegmentFlag=N;060-Rtd=1.803,060-SegmentFlag=N&&";

        SegmentConverter segmentConverter = new SegmentConverter();
        try {
            Segment s = segmentConverter.convert(data.toCharArray());

            assertEquals(s.getSt(),"32");
            assertEquals(s.getCn(),"2011");
            assertEquals(s.getPw(),"123456");
            assertEquals(s.getMn(),"LD130133000015");
            assertEquals(s.getCp(),"DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-SegmentFlag=N;060-Rtd=1.803,060-SegmentFlag=N");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }

    }
}