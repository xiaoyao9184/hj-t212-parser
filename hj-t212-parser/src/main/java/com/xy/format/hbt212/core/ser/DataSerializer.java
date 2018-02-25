package com.xy.format.hbt212.core.ser;

import com.xy.format.hbt212.core.T212Generator;
import com.xy.format.hbt212.core.VerifyUtil;
import com.xy.format.hbt212.core.converter.DataReverseConverter;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.Data;
import com.xy.format.hbt212.model.verify.PacketElement;
import com.xy.format.hbt212.model.verify.T212Map;
import com.xy.format.segment.base.cfger.Configurator;
import com.xy.format.segment.base.cfger.Configured;
import com.xy.format.segment.core.SegmentGenerator;
import com.xy.format.segment.core.ser.SegmentSerializer;
import com.xy.format.segment.exception.SegmentFormatException;

import javax.validation.Validator;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import static com.xy.format.hbt212.core.feature.VerifyFeature.DATA_LEN_RANGE;

/**
 * 对象 级别 序列化器
 * Created by xiaoyao9184 on 2018/2/24.
 */
public class DataSerializer
        implements T212Serializer<Data>, Configured<DataSerializer> {

    private int verifyFeature;
    private Configurator<SegmentGenerator> segmentGeneratorConfigurator;
    private SegmentSerializer<Map<String,Object>> segmentSerializer;
    private Configurator<DataReverseConverter> dataReverseConverterConfigurator;
    private Validator validator;

//    private int version;


    @Override
    public void configured(Configurator<DataSerializer> configurator){
        configurator.config(this);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void serialize(T212Generator generator, Data data) throws IOException, T212FormatException {
        generator.writeHeader();

        char[] segment = serialize(data);

        if(DATA_LEN_RANGE.enabledIn(verifyFeature)){
            int segmentLen = segment.length;
            VerifyUtil.verifyRange(segmentLen,0,1024, PacketElement.DATA_LEN);
        }
        generator.writeDataAndLenAndCrc(segment);
        generator.writeFooter();
    }

    public char[] serialize(Data data) throws IOException, T212FormatException {
        StringWriter writer = new StringWriter();
        SegmentGenerator generator = new SegmentGenerator(writer);
        generator.configured(segmentGeneratorConfigurator);

        Map<String,Object> map = convert(data);
        try {
            segmentSerializer.serialize(generator,map);
        } catch (SegmentFormatException e) {
            T212FormatException.segment_exception(e);
        }
        return writer.toString().toCharArray();
    }

    public T212Map<String,Object> convert(Data data) throws T212FormatException {
        DataReverseConverter dataConverter = new DataReverseConverter();
        dataConverter.configured(dataReverseConverterConfigurator);
        T212Map<String,Object> map = dataConverter.convert(data);

        return map;
    }

    public void setVerifyFeature(int verifyFeature) {
        this.verifyFeature = verifyFeature;
    }

    public void setSegmentGeneratorConfigurator(Configurator<SegmentGenerator> segmentGeneratorConfigurator) {
        this.segmentGeneratorConfigurator = segmentGeneratorConfigurator;
    }

    public void setSegmentSerializer(SegmentSerializer<Map<String, Object>> segmentSerializer) {
        this.segmentSerializer = segmentSerializer;
    }

    public void setDataReverseConverterConfigurator(Configurator<DataReverseConverter> dataReverseConverterConfigurator) {
        this.dataReverseConverterConfigurator = dataReverseConverterConfigurator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

}
