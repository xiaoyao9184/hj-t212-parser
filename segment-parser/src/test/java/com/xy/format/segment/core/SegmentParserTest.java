package com.xy.format.segment.core;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class SegmentParserTest {

    @Test
    public void test(){
        String data = "ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&";
        StringReader reader = new StringReader(data);
        SegmentParser parser = new SegmentParser(reader);

        try {
            parser.initToken();

            //ST=32;CN=2011;PW=123456;MN=LD130133000015;CP
            assertTrue(parser.readPathKey().startsWith("ST"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("32"));
            assertEquals(parser.currentToken(), SegmentToken.END_ENTRY);
            assertTrue(parser.readPathKey().startsWith("CN"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("2011"));
            assertEquals(parser.currentToken(), SegmentToken.END_ENTRY);
            assertTrue(parser.readPathKey().startsWith("PW"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("123456"));
            assertEquals(parser.currentToken(), SegmentToken.END_ENTRY);
            assertTrue(parser.readPathKey().startsWith("MN"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("LD130133000015"));
            assertEquals(parser.currentToken(), SegmentToken.END_ENTRY);
            assertTrue(parser.readPathKey().startsWith("CP"));

            //&&DataTime=20160824003817000;
            assertEquals(parser.currentToken(), SegmentToken.START_OBJECT_VALUE);
            assertTrue(parser.readPathKey().startsWith("DataTime"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("20160824003817000"));
            assertEquals(parser.currentToken(), SegmentToken.END_ENTRY);

            //B01-Rtd=36.91
            assertTrue(parser.readPathKey().startsWith("B01"));
            assertEquals(parser.currentToken(), SegmentToken.END_PART_KEY);
            assertTrue(parser.readPathKey().startsWith("Rtd"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("36.91"));
            assertEquals(parser.currentToken(), SegmentToken.END_ENTRY);

            //011-Rtd=231.0,011-Flag=N;
            assertTrue(parser.readPathKey().startsWith("011"));
            assertEquals(parser.currentToken(), SegmentToken.END_PART_KEY);
            assertTrue(parser.readPathKey().startsWith("Rtd"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("231.0"));
            assertEquals(parser.currentToken(), SegmentToken.END_SUB_ENTRY);

            assertTrue(parser.readPathKey().startsWith("011"));
            assertEquals(parser.currentToken(), SegmentToken.END_PART_KEY);
            assertTrue(parser.readPathKey().startsWith("Flag"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("N"));
            assertEquals(parser.currentToken(), SegmentToken.END_ENTRY);

            //060-Rtd=1.803,060-Flag=N&&
            assertTrue(parser.readPathKey().startsWith("060"));
            assertEquals(parser.currentToken(), SegmentToken.END_PART_KEY);
            assertTrue(parser.readPathKey().startsWith("Rtd"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("1.803"));
            assertEquals(parser.currentToken(), SegmentToken.END_SUB_ENTRY);

            assertTrue(parser.readPathKey().startsWith("060"));
            assertEquals(parser.currentToken(), SegmentToken.END_PART_KEY);
            assertTrue(parser.readPathKey().startsWith("Flag"));
            assertEquals(parser.currentToken(), SegmentToken.END_KEY);
            assertTrue(parser.readValue().startsWith("N"));
            assertEquals(parser.currentToken(), SegmentToken.END_OBJECT_VALUE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}