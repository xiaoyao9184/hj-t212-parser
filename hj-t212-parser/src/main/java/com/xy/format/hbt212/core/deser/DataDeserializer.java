package com.xy.format.hbt212.core.deser;

import com.xy.format.hbt212.core.converter.DataConverter;
import com.xy.format.hbt212.core.T212Parser;
import com.xy.format.hbt212.core.VerifyUtil;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.Data;
import com.xy.format.hbt212.model.DataFlag;
import com.xy.format.hbt212.model.verify.DataElement;
import com.xy.format.hbt212.model.verify.PacketElement;
import com.xy.format.hbt212.model.verify.T212Map;
import com.xy.format.hbt212.model.verify.groups.ModeGroup;
import com.xy.format.hbt212.model.verify.groups.VersionGroup;
import com.xy.format.segment.base.cfger.Configurator;
import com.xy.format.segment.base.cfger.Configured;
import com.xy.format.segment.core.SegmentParser;
import com.xy.format.segment.core.deser.SegmentDeserializer;
import com.xy.format.segment.exception.SegmentFormatException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.*;
import java.util.stream.Collectors;

import static com.xy.format.hbt212.core.T212Parser.crc16Checkout;
import static com.xy.format.hbt212.core.feature.VerifyFeature.*;
import static com.xy.format.hbt212.core.validator.clazz.FieldValidator.create_format_exception;

/**
 * 对象 级别 反序列化器
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class DataDeserializer
        implements T212Deserializer<Data>, Configured<DataDeserializer> {

    private int verifyFeature;
    private Configurator<SegmentParser> segmentParserConfigurator;
    private SegmentDeserializer<Map<String,Object>> segmentDeserializer;
    private Configurator<DataConverter> dataConverterConfigurator;
    private Validator validator;

//    private int version;


    @Override
    public void configured(Configurator<DataDeserializer> configurator){
        configurator.config(this);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Data deserialize(T212Parser parser) throws IOException, T212FormatException {
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
        return deserialize(data);
    }

    public Data deserialize(char[] data) throws IOException, T212FormatException {
        PushbackReader reader = new PushbackReader(new CharArrayReader(data));
        SegmentParser parser = new SegmentParser(reader);
        parser.configured(segmentParserConfigurator);

        Map<String,Object> result = null;
        try {
            result = segmentDeserializer.deserialize(parser);
        } catch (SegmentFormatException e) {
            T212FormatException.segment_exception(e);
        }
        return deserialize(result);
    }

    public Data deserialize(Map<String,Object> map) throws T212FormatException {
        DataConverter dataConverter = new DataConverter();
        dataConverter.configured(dataConverterConfigurator);
        Data result = dataConverter.convert(T212Map.createCpDataLevel(map));

        if(USE_VERIFICATION.enabledIn(verifyFeature)){
            verify(result);
        }
        return result;
    }

    private void verify(Data result) throws T212FormatException {
        List<Class> groups = new ArrayList<>();
        groups.add(Default.class);
        if(DataFlag.V0.isMarked(result.getDataFlag())){
            groups.add(VersionGroup.V2017.class);
        }else{
            groups.add(VersionGroup.V2005.class);
        }
        if(DataFlag.D.isMarked(result.getDataFlag())){
            groups.add(ModeGroup.UseSubPacket.class);
        }

        Set<ConstraintViolation<Data>> constraintViolationSet =
                validator.validate(result,groups.toArray(new Class[]{}));
        if(!constraintViolationSet.isEmpty()) {
            if(THROW_ERROR_VERIFICATION_FAILED.enabledIn(verifyFeature)){
                create_format_exception(constraintViolationSet,result);
            }else{
                //TODO set context
            }
        }
    }

    public void setVerifyFeature(int verifyFeature) {
        this.verifyFeature = verifyFeature;
    }

    public void setSegmentParserConfigurator(Configurator<SegmentParser> segmentParserConfigurator) {
        this.segmentParserConfigurator = segmentParserConfigurator;
    }

    public void setSegmentDeserializer(SegmentDeserializer<Map<String, Object>> segmentDeserializer) {
        this.segmentDeserializer = segmentDeserializer;
    }

    public void setDataConverterConfigurator(Configurator<DataConverter> mapperConfigurator) {
        this.dataConverterConfigurator = mapperConfigurator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

}
