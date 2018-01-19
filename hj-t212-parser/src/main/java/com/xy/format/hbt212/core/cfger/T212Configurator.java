package com.xy.format.hbt212.core.cfger;

import com.xy.format.hbt212.core.deser.CpDataLevelMapDeserializer;
import com.xy.format.hbt212.core.deser.DataDeserializer;
import com.xy.format.hbt212.core.deser.DataLevelMapDeserializer;
import com.xy.format.segment.base.cfger.Configurator;
import com.xy.format.segment.base.cfger.Configured;
import com.xy.format.segment.base.cfger.MultipleConfiguratorAdapter;
import com.xy.format.hbt212.core.T212Parser;
import com.xy.format.hbt212.core.deser.PackLevelDeserializer;
import com.xy.format.segment.core.SegmentParser;
import com.xy.format.segment.core.deser.MapSegmentDeserializer;
import com.xy.format.segment.core.deser.SegmentDeserializer;
import com.xy.format.segment.core.deser.StringMapSegmentDeserializer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * T212配置器
 * Created by xiaoyao9184 on 2018/1/9.
 */
public class T212Configurator
        extends MultipleConfiguratorAdapter {

    private int verifyFeature;
    private int parserFeature;


    public void setParserFeature(int parserFeature) {
        this.parserFeature = parserFeature;
    }

    public void setVerifyFeature(int verifyFeature) {
        this.verifyFeature = verifyFeature;
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
        public void config(PackLevelDeserializer parser) {
            T212Configurator.this.configure(parser);
        }
    }
    class DataLevelMapDeserializerConfigurator implements Configurator<DataLevelMapDeserializer>{
        @Override
        public void config(DataLevelMapDeserializer parser) {
            T212Configurator.this.configure(parser);
        }
    }
    class CpDataLevelMapDeserializerConfigurator implements Configurator<CpDataLevelMapDeserializer>{
        @Override
        public void config(CpDataLevelMapDeserializer parser) {
            T212Configurator.this.configure(parser);
        }
    }
    class DataDeserializerConfigurator implements Configurator<DataDeserializer>{
        @Override
        public void config(DataDeserializer parser) {
            T212Configurator.this.configure(parser);
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
                new DataDeserializerConfigurator()
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
        deserializer.setParserConfigurator(this::configure);
        deserializer.setSegmentParserConfigurator(this::configure);
        deserializer.setDataDeserializer(new StringMapSegmentDeserializer());
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param deserializer
     */
    public void configure(CpDataLevelMapDeserializer deserializer){
        deserializer.setVerifyFeature(verifyFeature);
        deserializer.setParserConfigurator(this::configure);
        deserializer.setSegmentParserConfigurator(this::configure);
        deserializer.setDataDeserializer(new MapSegmentDeserializer());
    }

    /**
     * 泛型方法实现
     * @see Configurator#config(Object)
     * @param deserializer
     */
    public void configure(DataDeserializer deserializer){
        deserializer.setVerifyFeature(verifyFeature);
        deserializer.setParserConfigurator(this::configure);
        deserializer.setSegmentParserConfigurator(this::configure);
        deserializer.setDataDeserializer(new MapSegmentDeserializer());
    }

}