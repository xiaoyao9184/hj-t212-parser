package com.xy.format.hbt212.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.format.hbt212.core.cfger.T212Configurator;
import com.xy.format.hbt212.core.deser.*;
import com.xy.format.hbt212.core.feature.ParserFeature;
import com.xy.format.hbt212.core.feature.VerifyFeature;
import com.xy.format.hbt212.core.ser.CpDataLevelMapDataSerializer;
import com.xy.format.hbt212.core.ser.DataSerializer;
import com.xy.format.hbt212.core.ser.PackLevelSerializer;
import com.xy.format.hbt212.core.ser.T212Serializer;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.Data;
import com.xy.format.segment.base.cfger.Feature;
import com.xy.format.segment.core.feature.SegmentParserFeature;

import javax.validation.Validator;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * T212映射器
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212Mapper {

    private static T212Factory t212FactoryProtoType;

    static {
        try {
            t212FactoryProtoType = new T212Factory();
            //注册 反序列化器
            t212FactoryProtoType.deserializerRegister(CpDataLevelMapDeserializer.class);
            t212FactoryProtoType.deserializerRegister(DataLevelMapDeserializer.class);
            t212FactoryProtoType.deserializerRegister(PackLevelDeserializer.class);
            t212FactoryProtoType.deserializerRegister(Map.class, CpDataLevelMapDeserializer.class);
            t212FactoryProtoType.deserializerRegister(Data.class, DataDeserializer.class);
            //默认 反序列化器
            t212FactoryProtoType.deserializerRegister(Object.class, CpDataLevelMapDeserializer.class);

            //注册 序列化器
            t212FactoryProtoType.serializerRegister(PackLevelSerializer.class);
            t212FactoryProtoType.serializerRegister(Data.class, DataSerializer.class);
            t212FactoryProtoType.serializerRegister(Map.class, CpDataLevelMapDataSerializer.class);
            //没有默认 序列化器
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private int verifyFeatures;
    private int parserFeatures;
    private T212Factory factory;
    private T212Configurator configurator;
    private Validator validator;
    private ObjectMapper objectMapper;


    public T212Mapper(){
        this.factory = t212FactoryProtoType.copy();
        this.configurator = new T212Configurator();
        this.validator = factory.validator();
        this.objectMapper = factory.objectMapper();
    }


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

    public T212Mapper configurator(T212Configurator configurator){
        this.configurator = configurator;
        return this;
    }

    public ObjectMapper objectMapper(){
        return this.objectMapper;
    }

    private T212Mapper applyConfigurator(){
        configurator.setSegmentParserFeature(this.parserFeatures >> SEGMENT_FEATURE_BIT_OFFSET);
        configurator.setParserFeature(this.parserFeatures & 0x00FF);
        configurator.setVerifyFeature(this.verifyFeatures);
        configurator.setValidator(this.validator);
        configurator.setObjectMapper(this.objectMapper);
        factory.setConfigurator(configurator);
        return this;
    }

    /*
    /**********************************************************
    /* Public API : read
    /* (mapping from T212 to Java types);
    /* main methods
    /**********************************************************
     */



    public <T> T readValue(InputStream is, Class<T> value) throws IOException, T212FormatException {
        applyConfigurator();
        return _readValueAndClose(factory.parser(is),value);
    }

    public <T> T readValue(byte[] bytes, Class<T> value) throws IOException, T212FormatException {
        applyConfigurator();
        return _readValueAndClose(factory.parser(bytes),value);
    }

    public <T> T readValue(Reader reader, Class<T> value) throws IOException, T212FormatException {
        applyConfigurator();
        return _readValueAndClose(factory.parser(reader),value);
    }

    public <T> T readValue(String data, Class<T> value) throws IOException, T212FormatException {
        applyConfigurator();
        return _readValueAndClose(factory.parser(data),value);
    }

    private <T> T _readValueAndClose(T212Parser parser, Class<T> value) throws IOException, T212FormatException {
        T212Deserializer<T> deserializer = factory.deserializerFor(value);
        try(T212Parser p = parser){
            return deserializer.deserialize(p);
        }catch (RuntimeException e){
            throw new T212FormatException("Runtime error",e);
        }
    }

    private <T> T _readValueAndClose(T212Parser parser, Type type, Class<T> value) throws IOException, T212FormatException {
        T212Deserializer<T> deserializer = factory.deserializerFor(type,value);
        try(T212Parser p = parser){
            return deserializer.deserialize(p);
        }catch (RuntimeException e){
            throw new T212FormatException("Runtime error",e);
        }
    }

    public <T> void writeValueAsStream(T value, Class<T> type, OutputStream outputStream) throws IOException, T212FormatException {
        applyConfigurator();
        _writeValueAndClose(factory.generator(outputStream),value,type);
    }

    public <T> void writeValueAsWriter(T value, Class<T> type, Writer writer) throws IOException, T212FormatException {
        applyConfigurator();
        _writeValueAndClose(factory.generator(writer),value,type);
    }

    private <T> void _writeValueAndClose(T212Generator generator, T value, Class<T> type) throws IOException, T212FormatException {
        T212Serializer<T> serializer = factory.serializerFor(type);
        try(T212Generator g = generator){
            serializer.serialize(g,value);
        }catch (RuntimeException e){
            throw new T212FormatException("Runtime error",e);
        }
    }



    private static Supplier<Type> getMapGenericType(){
        return () -> new Map<String,String>(){
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public String put(String key, String value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends String> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            public Collection<String> values() {
                return null;
            }

            @Override
            public Set<Entry<String, String>> entrySet() {
                return null;
            }
        }.getClass().getGenericInterfaces()[0];
    }

    private static Supplier<Type> getDeepMapGenericType(){
        return () -> new Map<String,Object>(){
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public String put(String key, Object value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends Object> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            public Collection<Object> values() {
                return null;
            }

            @Override
            public Set<Entry<String, Object>> entrySet() {
                return null;
            }
        }.getClass().getGenericInterfaces()[0];
    }

    public Map<String,String> readMap(InputStream is) throws IOException, T212FormatException {
        applyConfigurator();
        //noinspection unchecked
        return _readValueAndClose(factory.parser(is),getMapGenericType().get(),Map.class);
    }

    public Map<String,String> readMap(byte[] bytes) throws IOException, T212FormatException {
        applyConfigurator();
        //noinspection unchecked
        return _readValueAndClose(factory.parser(bytes),getMapGenericType().get(),Map.class);
    }

    public Map<String,String> readMap(Reader reader) throws IOException, T212FormatException {
        applyConfigurator();
        //noinspection unchecked
        return _readValueAndClose(factory.parser(reader),getMapGenericType().get(),Map.class);
    }

    public Map<String,String> readMap(String data) throws IOException, T212FormatException {
        applyConfigurator();
        //noinspection unchecked
        return _readValueAndClose(factory.parser(data),getMapGenericType().get(),Map.class);
    }


    public Map<String,Object> readDeepMap(InputStream is) throws IOException, T212FormatException {
        applyConfigurator();
        //noinspection unchecked
        return _readValueAndClose(factory.parser(is),getDeepMapGenericType().get(),Map.class);
    }

    public Map<String,Object> readDeepMap(byte[] bytes) throws IOException, T212FormatException {
        applyConfigurator();
        //noinspection unchecked
        return _readValueAndClose(factory.parser(bytes),getDeepMapGenericType().get(),Map.class);
    }

    public Map<String,Object> readDeepMap(Reader reader) throws IOException, T212FormatException {
        applyConfigurator();
        //noinspection unchecked
        return _readValueAndClose(factory.parser(reader),getDeepMapGenericType().get(),Map.class);
    }

    public Map<String,Object> readDeepMap(String data) throws IOException, T212FormatException {
        applyConfigurator();
        //noinspection unchecked
        return _readValueAndClose(factory.parser(data),getDeepMapGenericType().get(),Map.class);
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


    public String writeMapAsString(Map data) throws IOException, T212FormatException {
        StringWriter sw = new StringWriter();
        writeValueAsWriter(data,Map.class,sw);
        return sw.toString();
    }

    public char[] writeMapAsCharArray(Map data) throws IOException, T212FormatException {
        return writeMapAsString(data).toCharArray();
    }

    public String writeDataAsString(Data data) throws IOException, T212FormatException {
        StringWriter sw = new StringWriter();
        writeValueAsWriter(data,Data.class,sw);
        return sw.toString();
    }

    public char[] writeDataAsCharArray(Data data) throws IOException, T212FormatException {
        return writeDataAsString(data).toCharArray();
    }

}
