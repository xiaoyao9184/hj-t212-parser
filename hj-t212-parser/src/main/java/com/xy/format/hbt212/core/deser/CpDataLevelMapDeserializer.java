package com.xy.format.hbt212.core.deser;

import com.xy.format.hbt212.core.VerifyUtil;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.DataFlag;
import com.xy.format.hbt212.model.verify.PacketElement;
import com.xy.format.hbt212.model.verify.DataElement;
import com.xy.format.segment.base.cfger.Configurator;
import com.xy.format.segment.base.cfger.Configured;
import com.xy.format.hbt212.core.T212Parser;
import com.xy.format.segment.core.SegmentParser;
import com.xy.format.segment.core.deser.SegmentDeserializer;
import com.xy.format.segment.exception.SegmentFormatException;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.xy.format.hbt212.core.T212Parser.crc16Checkout;
import static com.xy.format.hbt212.core.feature.VerifyFeature.*;

/**
 * 数据区 级别 反序列化器
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class CpDataLevelMapDeserializer
        implements T212Deserializer<Map<String,Object>>, Configured<CpDataLevelMapDeserializer> {

    private Configurator<T212Parser> parserConfigurator;
    private Configurator<SegmentParser> segmentParserConfigurator;
    private int verifyFeature;
    private SegmentDeserializer<Map<String,Object>> dataDeserializer;

    @Override
    public void configured(Configurator<CpDataLevelMapDeserializer> configurator){
        configurator.config(this);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Map<String, Object> deserialize(T212Parser parser) throws IOException, T212FormatException {
        parser.configured(parserConfigurator);

        parser.readHeader();
        int len = parser.readInt32(10);
        if(len == -1){
            T212FormatException.length_not_range(PacketElement.DATA_LEN,len,4,4);
        }
        if(DATA_LEN_RANGE.enabledIn(verifyFeature)){
            VerifyUtil.verifyRange(len,0,1024, PacketElement.DATA_LEN);
        }
        char[] data = parser.readData(len);
        int crc = parser.readInt32(16);

        if(DATA_CRC.enabledIn(verifyFeature)){
            if(crc == -1 ||
                    crc16Checkout(data,len) != crc){
                T212FormatException.crc_verification_failed(PacketElement.DATA,data,crc);
            }
        }
        parser.readFooter();

        PushbackReader reader = new PushbackReader(new CharArrayReader(data));
        SegmentParser segmentParser = new SegmentParser(reader);
        return deserialize(segmentParser);
    }

    public Map<String, Object> deserialize(SegmentParser parser) throws IOException, T212FormatException {
        parser.configured(segmentParserConfigurator);

        Map<String,Object> result = null;
        try {
            result = dataDeserializer.deserialize(parser);
        } catch (SegmentFormatException e) {
            T212FormatException.segment_exception(e);
        }

        verify(result);
        return result;
    }


    private void verify(Map<String, Object> result) throws T212FormatException {
        if(!ALLOW_MISSING_FIELD.enabledIn(verifyFeature)){
            Stream<DataElement> stream = Stream.of(DataElement.values())
                    .filter(DataElement::isRequired);
            if(result.containsKey(DataElement.Flag.name())){
                String f = (String) result.get(DataElement.Flag.name());
                int flag = Integer.valueOf(f);
                if(DataFlag.D.isMarked(flag)){
                    stream = Stream.concat(stream,Stream.of(DataElement.PNO, DataElement.PNUM));
                }
            }

            Optional<DataElement> missing = stream
                    .filter(e -> !result.containsKey(e.name()))
                    .findFirst();
            if(missing.isPresent()){
                T212FormatException.field_is_missing(PacketElement.DATA,missing.get().name());
            }
        }

//        if(!ALLOW_VALUE_NOT_IN_RANGE.enabledIn(verifyFeature)){
//            Optional<DataElement> notInRange = result.entrySet()
//                    .stream()
//                    .map(kv -> new AbstractMap.SimpleEntry<>(
//                            DataElement.valueOf(kv.getKey()),
//                            kv.getValue()
//                    ))
//                    .filter(kv -> kv.getKey().equals(DataElement.CP))
//                    .filter(kv -> kv.getKey() != null)
//                    .filter(kv -> !kv.getKey().isVerify((String) kv.getValue()))
//                    .map(AbstractMap.SimpleEntry::getKey)
//                    .findFirst();
//            if(notInRange.isPresent()){
//                DataElement dataSchema = notInRange.get();
//                String value = (String) result.get(dataSchema.name());
//                int len = value == null ? 0 : value.length();
//                T212FormatException.length_not_range(PacketElement.DATA,len,dataSchema.getMin(),dataSchema.getMax());
//            }
//        }
//
        if(result.containsKey(DataElement.CP.name())){
            Map<String,Object> cp = (Map) result.get(DataElement.CP.name());
            if(!ALLOW_MISSING_FIELD.enabledIn(verifyFeature)){
                Stream<DataElement> stream = Stream.of(DataElement.values())
                        .filter(DataElement::isRequired);
                if(result.containsKey(DataElement.Flag.name())){
                    String f = (String) result.get(DataElement.Flag.name());
                    int flag = Integer.valueOf(f);
                    if(DataFlag.D.isMarked(flag)){
                        stream = Stream.concat(stream,Stream.of(DataElement.PNO, DataElement.PNUM));
                    }
                }

                Optional<DataElement> missing = stream
                        .filter(e -> !result.containsKey(e.name()))
                        .findFirst();
                if(missing.isPresent()){
                    T212FormatException.field_is_missing(PacketElement.DATA,missing.get().name());
                }
            }

//            if(!ALLOW_VALUE_NOT_IN_RANGE.enabledIn(verifyFeature)){
//                Optional<DataElement> notInRange = result.entrySet()
//                        .stream()
//                        .map(kv -> new AbstractMap.SimpleEntry<>(
//                                DataElement.valueOf(kv.getKey()),
//                                kv.getValue()
//                        ))
//                        .filter(kv -> kv.getKey().equals(DataElement.CP))
//                        .filter(kv -> kv.getKey() != null)
//                        .filter(kv -> !kv.getKey().isVerify((String) kv.getValue()))
//                        .map(AbstractMap.SimpleEntry::getKey)
//                        .findFirst();
//                if(notInRange.isPresent()){
//                    DataElement dataSchema = notInRange.get();
//                    String value = (String) result.get(dataSchema.name());
//                    int len = value == null ? 0 : value.length();
//                    T212FormatException.length_not_range(PacketElement.DATA,len,dataSchema.getMin(),dataSchema.getMax());
//                }
//            }
        }
    }

    public void setVerifyFeature(int verifyFeature) {
        this.verifyFeature = verifyFeature;
    }

    public void setParserConfigurator(Configurator<T212Parser> parserConfigurator) {
        this.parserConfigurator = parserConfigurator;
    }

    public void setSegmentParserConfigurator(Configurator<SegmentParser> segmentParserConfigurator) {
        this.segmentParserConfigurator = segmentParserConfigurator;
    }

    public void setDataDeserializer(SegmentDeserializer<Map<String, Object>> dataDeserializer) {
        this.dataDeserializer = dataDeserializer;
    }
}
