package com.xy.format.hbh212;

import com.xy.format.hbh212.model.Data;
import com.xy.format.hbh212.model.H212;
import com.xy.format.hbh212.model.Pack;
import com.xy.format.hbh212.model.Segment;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2017/12/19.
 */
public class H212ParserTest {

    @Test
    public void parsePack() {
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        try {
            Pack pack = H212Parser.I.parsePack(data);

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

    @Test
    public void parseSegment() {
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        try {
            Segment s = H212Parser.I.parseSegment(data);

            assertEquals(s.getSt(),"32");
            assertEquals(s.getCn(),"2011");
            assertEquals(s.getPw(),"123456");
            assertEquals(s.getMn(),"LD130133000015");
            assertEquals(s.getCp(),"DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseData() {
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        try {
            Data d = H212Parser.I.parseData(data);

            assertEquals(d.getDataTime(),"20160824003817000");
            assertEquals(d.getPollution().size(),3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse() {
        String data = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        try {
            H212 h212 = H212Parser.I.parse(data);
            Data d = h212.getCp();

            assertEquals(h212.getSt(),"32");
            assertEquals(h212.getCn(),"2011");
            assertEquals(h212.getPw(),"123456");
            assertEquals(h212.getMn(),"LD130133000015");

            assertEquals(d.getDataTime(),"20160824003817000");
            assertEquals(d.getPollution().size(),3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void parseMany(){
        Reflections reflections = new Reflections("", new ResourcesScanner());
        Set<String> fileNames = reflections.getResources(Pattern.compile(".*\\.h212"));

        List<String> packets = fileNames.stream()
                .flatMap(fileName -> {
                    try {
                        URI uri = this.getClass().getClassLoader().getResource(fileName).toURI();
                        return Files.readAllLines(Paths.get(uri))
                                .stream()
                                .map(line -> line + "\r\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Stream.empty();
                })
                .collect(Collectors.toList());

        List<String> alreadyKnowError = packets.stream()
                .filter(p ->
                        p.contains("HeartbeatTime") ||
                        p.contains(",AlarmType") ||
                        p.contains("ZsCou") ||
                        p.contains("ZsFlag"))
                .collect(Collectors.toList());

        List<Map.Entry> unknowError = packets.stream()
                .filter(p -> !p.contains("HeartbeatTime"))
                .filter(p -> !p.contains(",AlarmType"))
                .filter(p -> !p.contains("ZsCou"))
                .filter(p -> !p.contains("ZsFlag"))
                .map(p -> {
                    try {
                        H212 h = H212Parser.I.parse(p);
                        return new AbstractMap.SimpleEntry<String, Object>(p,h);
                    } catch (Exception e) {
                        int i = packets.indexOf(p);
                        logger.error("packet {}/{} error : {}", i, packets.size(), e.getMessage());
                        logger.error("\n'{}'", p);
                        return new AbstractMap.SimpleEntry<String, Object>(p,e);
                    }
                })
                .filter(kv -> !(kv.getValue() instanceof H212))
                .collect(Collectors.toList());
//                .collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));

        int errorCount = alreadyKnowError.size() + unknowError.size();
        int okCount = packets.size() - errorCount;
        assertTrue(okCount > errorCount);

    }
}
