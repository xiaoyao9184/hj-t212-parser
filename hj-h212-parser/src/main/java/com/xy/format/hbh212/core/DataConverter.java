package com.xy.format.hbh212.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.format.hbh212.exception.FormatException;
import com.xy.format.hbh212.model.Data;
import com.xy.format.hbh212.model.Segment;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class DataConverter {

    public enum Mode {
        KEY,
        VALUE,
        SUB_KEY,
        SUB_VALUE;
    }

    public Map<String,Object> convertToMap(Segment segment) throws IOException, FormatException {
        StringReader reader = new StringReader(segment.getCp());

        Map<String,Object> kvMap = new LinkedHashMap<>();
        Map<String,Object> kvMapNow = kvMap;
        StringWriter writer = new StringWriter();
        String key = null;
        String value = null;
        Mode mode = Mode.KEY;

        int i = 0;
        while((i = reader.read()) != -1) {
            char c = (char)i;

            if(c == '='){
                if(!(mode == Mode.KEY ||
                        mode == Mode.SUB_KEY)){
                    FormatException.separator_position(c,mode);
                }
                //build key
                writer.flush();
                key = writer.toString();
                writer = new StringWriter();

                if(mode == Mode.KEY){
                    //next to Value
                    mode = Mode.VALUE;
                    continue;
                }else if(mode == Mode.SUB_KEY){
                    //next to Sub Value
                    mode = Mode.SUB_VALUE;
                    continue;
                }
            }
            if(c == ';'){
                if(!(mode == Mode.VALUE ||
                        mode == Mode.SUB_VALUE)){
                    FormatException.separator_position(c,mode);
                }
                //build (Sub)Key Value
                writer.flush();
                value = writer.toString();
                kvMapNow.put(key,value);
                writer = new StringWriter();
                key = null;
                value = null;

                //next to Key
                mode = Mode.KEY;
                kvMapNow = kvMap;
                continue;
            }
            if(c == ','){
                if(mode != Mode.SUB_VALUE){
                    FormatException.separator_position(c,mode);
                }
                //build Sub Key Value
                writer.flush();
                value = writer.toString();
                kvMapNow.put(key,value);
                writer = new StringWriter();
                key = null;
                value = null;

                //next to Sub Key
                mode = Mode.SUB_KEY;
                continue;
            }
            if(c == '-'){
                if(writer.getBuffer().length() == 0 &&
                        mode == Mode.SUB_VALUE){
                    //允许SUB_KEY中一个字符是-，存在负数
                    writer.append(c);
                    continue;
                }
                if(!(mode == Mode.KEY ||
                        mode == Mode.SUB_KEY)){
                    FormatException.separator_position(c,mode);
                }

                if(mode == Mode.KEY){
                    //build Key Map
                    writer.flush();
                    key = writer.toString();
                    kvMapNow = new LinkedHashMap<>();
                    kvMap.put(key,kvMapNow);
                }else if(mode == Mode.SUB_KEY){
                    //ignore Key
                }
                writer = new StringWriter();

                //Sub Key
                mode = Mode.SUB_KEY;
                continue;
            }
            writer.append(c);
        }
        //build last (Sub)Key Value
        writer.flush();
        value = writer.toString();
        kvMapNow.put(key,value);

        return kvMap;
    }

    public Data convert(Segment segment) throws IOException, FormatException {
        Map<String,Object> kvMap = convertToMap(segment);

        Map<String,Object> pollutionMap = kvMap.entrySet().stream()
                .filter(kv -> kv.getValue() instanceof Map)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        pollutionMap.keySet().forEach(kvMap::remove);
        kvMap.put("Pollution",pollutionMap);

        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Data data = objectMapper.convertValue(kvMap,Data.class);

        //默认数据
        if(data.getQn() == null){
            data.setQn(segment.getQn());
        }
        if(data.getpNum() == null){
            data.setpNum(segment.getpNum());
        }
        if(data.getpNo() == null){
            data.setpNo(segment.getpNo());
        }
        if(data.getPw() == null){
            data.setPw(segment.getPw());
        }
        if(data.getSegmentFlag() == null){
            data.setSegmentFlag(segment.getSegmentFlag());
        }

        return data;
    }
}
