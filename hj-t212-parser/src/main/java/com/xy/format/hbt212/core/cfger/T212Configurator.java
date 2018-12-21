package com.xy.format.hbt212.core.cfger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.format.hbt212.core.T212Generator;
import com.xy.format.hbt212.core.T212Parser;
import com.xy.format.hbt212.core.converter.DataConverter;
import com.xy.format.hbt212.core.converter.DataReverseConverter;
import com.xy.format.hbt212.core.deser.CpDataLevelMapDeserializer;
import com.xy.format.hbt212.core.deser.DataDeserializer;
import com.xy.format.hbt212.core.deser.DataLevelMapDeserializer;
import com.xy.format.hbt212.core.deser.PackLevelDeserializer;
import com.xy.format.hbt212.core.ser.CpDataLevelMapDataSerializer;
import com.xy.format.hbt212.core.ser.DataSerializer;
import com.xy.format.hbt212.core.ser.PackLevelSerializer;
import com.xy.format.hbt212.core.ser.T212CpMapPathValueSegmentSerializer;
import com.xy.format.segment.base.cfger.Configurator;
import com.xy.format.segment.base.cfger.MultipleConfiguratorAdapter;
import com.xy.format.segment.core.SegmentGenerator;
import com.xy.format.segment.core.SegmentParser;
import com.xy.format.segment.core.deser.MapSegmentDeserializer;
import com.xy.format.segment.core.deser.StringMapSegmentDeserializer;
import com.xy.format.segment.core.ser.MapSegmentSerializer;

import javax.validation.Validator;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * T212配置器
 * Created by xiaoyao9184 on 2018/1/9.
 */
public class T212Configurator
        extends MultipleConfiguratorAdapter {

    // 定义 segment 项目中 key 和 value 所占字节长度
    private static final int SEGMENT_KEY_MAX_LENGTH = 20;
    private static final int SEGMENT_VALUE_MAX_LENGTH = 1024;
    private static final int SEGMENT_VALUE_NORMAL_LENGTH = 50;


    private int segmentParserFeature;
    private int parserFeature;

    private int verifyFeature;
    private Validator validator;
    private ObjectMapper objectMapper;

    private int segmentGeneratorFeature;
    private final int generatorFeature = 0;

    public void setSegmentParserFeature(int segmentParserFeature) {
        this.segmentParserFeature = segmentParserFeature;
    }

    public void setParserFeature(int parserFeature) {
        this.parserFeature = parserFeature;
    }

    public void setVerifyFeature(int verifyFeature) {
        this.verifyFeature = verifyFeature;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setSegmentGeneratorFeature(int segmentGeneratorFeature) {
        this.segmentGeneratorFeature = segmentGeneratorFeature;
    }


    class SegmentParserConfigurator implements Configurator<SegmentParser>{
        @Override
        public void config(SegmentParser parser) {
            T212Configurator.this.configure(parser);
        }
    }
    class T212ParserConfigurator implements Configurator<T212Parser>{
        @Override
        public void config(T212Parser parser) {
            T212Configurator.this.configure(parser);
        }
    }
    class PackLevelDeserializerConfigurator implements Configurator<PackLevelDeserializer>{
        @Override
        public void config(PackLevelDeserializer deserializer) {
            T212Configurator.this.configure(deserializer);
        }
    }
    class DataLevelMapDeserializerConfigurator implements Configurator<DataLevelMapDeserializer>{
        @Override
        public void config(DataLevelMapDeserializer deserializer) {
            T212Configurator.this.configure(deserializer);
        }
    }
    class CpDataLevelMapDeserializerConfigurator implements Configurator<CpDataLevelMapDeserializer>{
        @Override
        public void config(CpDataLevelMapDeserializer deserializer) {
            T212Configurator.this.configure(deserializer);
        }
    }
    class DataDeserializerConfigurator implements Configurator<DataDeserializer>{
        @Override
        public void config(DataDeserializer deserializer) {
            T212Configurator.this.configure(deserializer);
        }
    }
    class DataConverterConfigurator implements Configurator<DataConverter>{
        @Override
        public void config(DataConverter converter) {
            T212Configurator.this.configure(converter);
        }
    }

    class SegmentGeneratorConfigurator implements Configurator<SegmentGenerator>{
        @Override
        public void config(SegmentGenerator generator) {
            T212Configurator.this.configure(generator);
        }
    }
    class T212GeneratorConfigurator implements Configurator<T212Generator>{
        @Override
        public void config(T212Generator generator) {
            T212Configurator.this.configure(generator);
        }
    }
    class PackLevelSerializerConfigurator implements Configurator<PackLevelSerializer>{
        @Override
        public void config(PackLevelSerializer serializer) {
            T212Configurator.this.configure(serializer);
        }
    }
    class CpDataLevelMapDataSerializerConfigurator implements Configurator<CpDataLevelMapDataSerializer>{
        @Override
        public void config(CpDataLevelMapDataSerializer serializer) {
            T212Configurator.this.configure(serializer);
        }
    }
    class DataSerializerConfigurator implements Configurator<DataSerializer>{
        @Override
        public void config(DataSerializer serializer) {
            T212Configurator.this.configure(serializer);
        }
    }
    class DataReverseConverterConfigurator implements Configurator<DataReverseConverter>{
        @Override
        public void config(DataReverseConverter converter) {
            T212Configurator.this.configure(converter);
        }
    }



    @Override
    public Collection<Configurator> configurators() {
        return Stream.of(
                new SegmentParserConfigurator(),
                new T212ParserConfigurator(),
                new PackLevelDeserializerConfigurator(),
                new DataLevelMapDeserializerConfigurator(),
                new CpDataLevelMapDeserializerConfigurator(),
                new DataDeserializerConfigurator(),
                new DataConverterConfigurator(),

                new SegmentGeneratorConfigurator(),
                new T212GeneratorConfigurator(),
                new PackLevelSerializerConfigurator(),
                new CpDataLevelMapDataSerializerConfigurator(),
                new DataSerializerConfigurator(),
                new DataReverseConverterConfigurator()
//                (Configurator<SegmentParser>)this::configure,
//                (Configurator<T212Parser>)this::configure,
//                (Configurator<PackLevelDeserializer>)this::configure,
//                (Configurator<DataLevelMapDeserializer>)this::configure,
//                (Configurator<CpDataLevelMapDeserializer>)this::configure
        )
                .collect(Collectors.toSet());
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param parser
     */
    public void configure(SegmentParser parser){
        if(parser.currentToken() == null){
            parser.initToken();
        }
        parser.setParserFeature(segmentParserFeature);
        parser.setKeyMaxLength(SEGMENT_KEY_MAX_LENGTH);
        parser.setValueMaxLength(SEGMENT_VALUE_MAX_LENGTH);
        parser.setValueNormalLength(SEGMENT_VALUE_NORMAL_LENGTH);
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param parser
     */
    public void configure(T212Parser parser){
        parser.setParserFeature(parserFeature);
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param deserializer
     */
    public void configure(PackLevelDeserializer deserializer){
        deserializer.setVerifyFeature(verifyFeature);
        deserializer.setParserConfigurator(this::configure);
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param deserializer
     */
    public void configure(DataLevelMapDeserializer deserializer){
        deserializer.setVerifyFeature(verifyFeature);
        deserializer.setValidator(validator);
        deserializer.setSegmentParserConfigurator(this::configure);
        deserializer.setSegmentDeserializer(new StringMapSegmentDeserializer());
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param deserializer
     */
    public void configure(CpDataLevelMapDeserializer deserializer){
        deserializer.setVerifyFeature(verifyFeature);
        deserializer.setValidator(validator);
        deserializer.setSegmentParserConfigurator(this::configure);
        deserializer.setSegmentDeserializer(new MapSegmentDeserializer());
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param deserializer
     */
    public void configure(DataDeserializer deserializer){
        deserializer.setVerifyFeature(verifyFeature);
        deserializer.setValidator(validator);
        deserializer.setSegmentParserConfigurator(this::configure);
        deserializer.setSegmentDeserializer(new MapSegmentDeserializer());
        deserializer.setDataConverterConfigurator(this::configure);
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param dataConverter
     */
    public void configure(DataConverter dataConverter){
        ObjectMapper objectMapper = this.objectMapper;
        if(objectMapper == null){
            objectMapper = new ObjectMapper()
                    .configure(FAIL_ON_UNKNOWN_PROPERTIES,false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//                    .addMixIn(Data.class, DataDeserializationMixin.class)
//                    .addMixIn(CpData.class, CpDataDeserializationMixin.class);
//            objectMapper.getSerializationConfig()
//                    .getDefaultVisibilityChecker()
//                    .withFieldVisibility(JsonAutoDetect.Visibility.NONE)
//                    .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
//                    .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
//                    .withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
        }
        dataConverter.setObjectMapper(objectMapper);
    }


    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param generator
     */
    private void configure(SegmentGenerator generator){
        if(generator.nextToken() == null){
            generator.initToken();
        }
        generator.setGeneratorFeature(segmentGeneratorFeature);
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param generator
     */
    private void configure(T212Generator generator) {
        generator.setGeneratorFeature(generatorFeature);
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param serializer
     */
    private void configure(PackLevelSerializer serializer) {
        serializer.setVerifyFeature(verifyFeature);
        serializer.setGeneratorConfigurator(this::configure);
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param serializer
     */
    public void configure(CpDataLevelMapDataSerializer serializer){
        serializer.setVerifyFeature(verifyFeature);
        serializer.setValidator(validator);
        serializer.setSegmentGeneratorConfigurator(this::configure);
        serializer.setSegmentSerializer(new MapSegmentSerializer(new T212CpMapPathValueSegmentSerializer()));
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param serializer
     */
    private void configure(DataSerializer serializer) {
        serializer.setVerifyFeature(verifyFeature);
        serializer.setValidator(validator);
        serializer.setSegmentGeneratorConfigurator(this::configure);
        serializer.setSegmentSerializer(new MapSegmentSerializer(new T212CpMapPathValueSegmentSerializer()));
        serializer.setDataReverseConverterConfigurator(this::configure);
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param dataReverseConverter
     */
    private void configure(DataReverseConverter dataReverseConverter) {
        ObjectMapper objectMapper = this.objectMapper;
        if(objectMapper == null){
            objectMapper = new ObjectMapper()
                    .configure(FAIL_ON_UNKNOWN_PROPERTIES,false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        dataReverseConverter.setObjectMapper(objectMapper);
    }

}
