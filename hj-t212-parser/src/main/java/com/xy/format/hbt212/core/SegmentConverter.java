package com.xy.format.hbt212.core;

import com.xy.format.hbt212.exception.FormatException;
import com.xy.format.hbt212.model.SegmentFlag;
import com.xy.format.hbt212.model.Segment;
import com.xy.format.hbt212.model.schema.SegmentSchema;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class SegmentConverter {

    public enum Mode {
        KEY,
        VALUE,
        DATA_VALUE;
    }


    public Map<String,String> convertToMap(char[] chars) throws IOException, FormatException {
        CharArrayReader car = new CharArrayReader(chars);

        Map<String,String> kvMap = new LinkedHashMap<>();
        String key = null;
        String value = null;
        StringWriter sw = new StringWriter();
        Mode mode = Mode.KEY;

        int i = 0;
        while((i = car.read()) != -1) {
            char c = (char)i;

            if(c == '='){
                if(mode == Mode.DATA_VALUE){
                    sw.append(c);
                    continue;
                }else if(mode != Mode.KEY){
                    FormatException.separator_position(c,mode);
                }
                sw.flush();
                key = sw.toString();
                sw = new StringWriter();
                //next to Value
                mode = Mode.VALUE;
                continue;
            }
            if(c == ';'){
                if(mode == Mode.DATA_VALUE){
                    sw.append(c);
                    continue;
                }else if(mode != Mode.VALUE){
                    FormatException.separator_position(c,mode);
                }
                sw.flush();
                value = sw.toString();
                kvMap.put(key,value);
                sw = new StringWriter();
                key = null;
                value = null;

                //next to Key
                mode = Mode.KEY;
                continue;
            }
            if(c == '&'){
                car.mark(-0);
                if(car.read() == i){
                    //&&
                    if(mode == Mode.VALUE){
                        //Data Value
                        mode = Mode.DATA_VALUE;
                        continue;
                    }else if(mode == Mode.DATA_VALUE){
                        sw.flush();
                        value = sw.toString();
                        kvMap.put(key,value);
                        sw = new StringWriter();
                        key = null;
                        value = null;

                        //ignore
                        mode = Mode.KEY;
                        continue;
                    }
                }
                car.reset();
            }
            sw.append(c);
        }

        return kvMap;
    }


    public Segment convert(char[] chars) throws IOException, FormatException {
        Map<String,String> kvMap = convertToMap(chars);

        Segment segment = new Segment();

        //TODO 协议描述错误
        //位数不固定
        Verify.verifyLen(
                kvMap.get(SegmentSchema.QN.name()),
                SegmentSchema.QN.getMin(),
                SegmentSchema.QN.getMax(),
                SegmentSchema.QN);
        segment.setQn(kvMap.get(SegmentSchema.QN.name()));

        Verify.verifyLen(
                kvMap.get(SegmentSchema.PNUM.name()),
                SegmentSchema.PNUM.getMax(),
                SegmentSchema.PNUM);
        segment.setpNum(kvMap.get(SegmentSchema.PNUM.name()));

        Verify.verifyLen(
                kvMap.get(SegmentSchema.PNO.name()),
                SegmentSchema.PNO.getMax(),
                SegmentSchema.PNO);
        segment.setpNo(kvMap.get(SegmentSchema.PNO.name()));

        //TODO 协议描述错误
        //位数不固定
        Verify.verifyLen(
                kvMap.get(SegmentSchema.ST.name()),
                SegmentSchema.ST.getMin(),
                SegmentSchema.ST.getMax(),
                SegmentSchema.ST);
        segment.setSt(kvMap.get(SegmentSchema.ST.name()));

        //TODO 协议描述错误
        Verify.verifyLen(
                kvMap.get(SegmentSchema.CN.name()),
                SegmentSchema.CN.getMin(),
                SegmentSchema.CN.getMax(),
                SegmentSchema.CN);
        segment.setCn(kvMap.get(SegmentSchema.CN.name()));

        Verify.verifyLen(
                kvMap.get(SegmentSchema.PW.name()),
                SegmentSchema.PW.getMin(),
                SegmentSchema.PW);
        segment.setPw(kvMap.get(SegmentSchema.PW.name()));

        Verify.verifyLen(
                kvMap.get(SegmentSchema.MN.name()),
                SegmentSchema.MN.getMin(),
                SegmentSchema.MN);
        segment.setMn(kvMap.get(SegmentSchema.MN.name()));

        Verify.verifyRange(
                kvMap.get(SegmentSchema.Flag.name()),
                SegmentSchema.Flag.getMin(),
                SegmentSchema.Flag.getMax(),
                SegmentSchema.Flag);
        String flag = kvMap.get(SegmentSchema.Flag.name());
        if(flag != null){
            int i = Integer.parseInt(flag);
            Verify.verifyRange(i,0,255, SegmentSchema.Flag);

            List<SegmentFlag> segmentFlagList = new ArrayList<>();

            if((i & SegmentFlag.A.getBit()) == SegmentFlag.A.getBit()){
                segmentFlagList.add(SegmentFlag.A);
            }
            if((i & SegmentFlag.D.getBit()) == SegmentFlag.D.getBit()){
                segmentFlagList.add(SegmentFlag.D);
            }
            segment.setSegmentFlag(segmentFlagList);
        }

        Verify.verifyRange(
                kvMap.get(SegmentSchema.CP.name()).length(),
                SegmentSchema.CP.getMin(),
                SegmentSchema.CP.getMax(),
                SegmentSchema.CP);

        segment.setCp(kvMap.get(SegmentSchema.CP.name()));

        return segment;
    }
}
