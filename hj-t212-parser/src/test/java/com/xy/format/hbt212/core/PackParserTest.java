package com.xy.format.hbt212.core;

import com.xy.format.hbt212.model.Pack;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/12/19.
 */
public class PackParserTest {

    @Test
    public void parse() {
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());
        PackParser parser = new PackParser();
        try {
            Pack pack = parser.parse(bais);

            assertEquals(pack.getLength()[0],'0');
            assertEquals(pack.getLength()[1],'1');
            assertEquals(pack.getLength()[2],'3');
            assertEquals(pack.getLength()[3],'9');

            assertEquals(pack.getCrc()[0],'4');
            assertEquals(pack.getCrc()[1],'9');
            assertEquals(pack.getCrc()[2],'8');
            assertEquals(pack.getCrc()[3],'0');
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}