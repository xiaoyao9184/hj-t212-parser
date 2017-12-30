package com.xy.format.hbt212;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.format.hbt212.core.DataConverter;
import com.xy.format.hbt212.core.PackParser;
import com.xy.format.hbt212.core.SegmentConverter;
import com.xy.format.hbt212.model.Data;
import com.xy.format.hbt212.model.H212;
import com.xy.format.hbt212.model.Pack;
import com.xy.format.hbt212.model.Segment;
import com.xy.format.hbt212.model.mixin.SegmentMixIn;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by xiaoyao9184 on 2017/12/12.
 */
public enum H212Parser {
    I;

    private Pack parsePack(InputStream is) throws Exception {
        Pack pack = new PackParser().parse(is);
        return pack;
    }

    public Pack parsePack(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return parsePack(bais);
    }

    public Pack parsePack(String msg) throws Exception {
        return parsePack(msg.getBytes());
    }



    private Segment parseSegment(InputStream is) throws Exception {
        SegmentConverter sc = new SegmentConverter();
        return sc.convert(parsePack(is).getSegment());
    }

    public Segment parseSegment(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return parseSegment(bais);
    }

    public Segment parseSegment(String msg) throws Exception {
        return parseSegment(msg.getBytes());
    }



    private Data parseData(InputStream is) throws Exception {
        SegmentConverter sc = new SegmentConverter();
        Segment segment = sc.convert(parsePack(is).getSegment());
        DataConverter dc = new DataConverter();
        return dc.convert(segment);
    }

    public Data parseData(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return parseData(bais);
    }

    public Data parseData(String msg) throws Exception {
        return parseData(msg.getBytes());
    }




    private H212 parse(InputStream is) throws Exception {
        SegmentConverter sc = new SegmentConverter();
        Segment segment = sc.convert(parsePack(is).getSegment());
        DataConverter dc = new DataConverter();
        Data data = dc.convert(segment);

//        String j = new ObjectMapper()
////                .configure(MapperFeature.USE_ANNOTATIONS,false)
//                .addMixIn(Segment.class, SegmentMixIn.class)
////                .writer().withoutAttribute("CP")
//                .writeValueAsString(segment);

        H212 h212 = new ObjectMapper()
                .addMixIn(Segment.class, SegmentMixIn.class)
                .convertValue(segment,H212.class);
        h212.setCp(data);
        return h212;
    }

    public H212 parse(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return parse(bais);
    }

    public H212 parse(String msg) throws Exception {
        return parse(msg.getBytes());
    }
}
