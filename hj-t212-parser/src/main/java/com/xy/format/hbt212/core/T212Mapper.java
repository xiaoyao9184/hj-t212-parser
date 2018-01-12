package com.xy.format.hbt212.core;

import com.xy.format.hbt212.core.deser.*;
import com.xy.format.hbt212.core.feature.ParserFeature;
import com.xy.format.hbt212.core.feature.VerifyFeature;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.Data;
import com.xy.format.segment.base.cfger.Feature;
import com.xy.format.segment.core.feature.SegmentParserFeature;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

/**
 * T212映射器
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212Mapper {

    static {
        try {
            //注册 反序列化器
            T212Factory.I.deserializerRegister(CpDataLevelMapDeserializer.class);
            T212Factory.I.deserializerRegister(DataLevelMapDeserializer.class);
            T212Factory.I.deserializerRegister(PackLevelDeserializer.class);
            //默认 反序列化器
                T212Factory.I.deserializerRegister(Data.class, DataDeserializer.class);
            T212Factory.I.deserializerRegister(Object.class, CpDataLevelMapDeserializer.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private int verifyFeatures;
    private int parserFeatures;


    /*
    /**********************************************************
    /* Configuration
    /**********************************************************
     */

    private static int SEGMENT_FEATURE_BIT_OFFSET = 8;

    public T212Mapper enableDefaultParserFeatures() {
        parserFeatures = Feature.collectFeatureDefaults(SegmentParserFeature.class);
        parserFeatures = parserFeatures << SEGMENT_FEATURE_BIT_OFFSET;
        parserFeatures = parserFeatures | Feature.collectFeatureDefaults(ParserFeature.class);
        return this;
    }

    public T212Mapper enableDefaultVerifyFeatures() {
        verifyFeatures = verifyFeatures | Feature.collectFeatureDefaults(VerifyFeature.class);
        return this;
    }


    public T212Mapper enable(SegmentParserFeature feature) {
        parserFeatures = parserFeatures | feature.getMask() << SEGMENT_FEATURE_BIT_OFFSET;
        return this;
    }

    public T212Mapper enable(ParserFeature feature) {
        parserFeatures = parserFeatures | feature.getMask();
        return this;
    }

    public T212Mapper enable(VerifyFeature feature) {
        verifyFeatures = verifyFeatures | feature.getMask();
        return this;
    }


    public T212Mapper disable(SegmentParserFeature feature) {
        parserFeatures = parserFeatures & ~(feature.getMask() << SEGMENT_FEATURE_BIT_OFFSET);
        return this;
    }

    public T212Mapper disable(ParserFeature feature) {
        parserFeatures = parserFeatures & ~feature.getMask();
        return this;
    }

    public T212Mapper disable(VerifyFeature feature) {
        verifyFeatures = verifyFeatures & ~feature.getMask();
        return this;
    }

    /*
    /**********************************************************
    /* Public API (from ObjectCodec): deserialization
    /* (mapping from T212 to Java types);
    /* main methods
    /**********************************************************
     */



    public <T> T readValue(InputStream is, Class<T> value) throws IOException, T212FormatException {
        return _readValueAndClose(T212Factory.I.parser(is),value);
    }

    public <T> T readValue(byte[] bytes, Class<T> value) throws IOException, T212FormatException {
        return _readValueAndClose(T212Factory.I.parser(bytes),value);
    }

    public <T> T readValue(Reader reader, Class<T> value) throws IOException, T212FormatException {
        return _readValueAndClose(T212Factory.I.parser(reader),value);
    }

    public <T> T readValue(String data, Class<T> value) throws IOException, T212FormatException {
        return _readValueAndClose(T212Factory.I.parser(data),value);
    }

    private <T> T _readValueAndClose(T212Parser parser, Class<T> value) throws IOException, T212FormatException {
        T212Deserializer<T> deserializer = T212Factory.I.deserializerFor(value);
        try(T212Parser p = parser){
            return deserializer.deserialize(p);
        }
    }



    public Map<String,String> readMap(InputStream is) throws IOException, T212FormatException {
        //noinspection unchecked
        return readValue(is,Map.class);
    }

    public Map<String,String> readMap(byte[] bytes) throws IOException, T212FormatException {
        //noinspection unchecked
        return readValue(bytes,Map.class);
    }

    public Map<String,String> readMap(Reader reader) throws IOException, T212FormatException {
        //noinspection unchecked
        return readValue(reader,Map.class);
    }

    public Map<String,String> readMap(String data) throws IOException, T212FormatException {
        //noinspection unchecked
        return readValue(data,Map.class);
    }


    public Data readData(InputStream is) throws IOException, T212FormatException {
        //noinspection unchecked
        return readValue(is,Data.class);
    }

    public Data readData(byte[] bytes) throws IOException, T212FormatException {
        //noinspection unchecked
        return readValue(bytes,Data.class);
    }

    public Data readData(Reader reader) throws IOException, T212FormatException {
        //noinspection unchecked
        return readValue(reader,Data.class);
    }

    public Data readData(String data) throws IOException, T212FormatException {
        //noinspection unchecked
        return readValue(data,Data.class);
    }

}
